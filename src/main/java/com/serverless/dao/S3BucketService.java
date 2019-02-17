package com.serverless.dao;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.exceptions.HttpException;
import com.serverless.exceptions.InternalServerErrorException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;


public class S3BucketService implements IS3BucketService
{
    private final ObjectMapper objectMapper;
    private final TransferManager transferManager;
    private final AmazonS3 amazonS3;
    private static final Logger LOG= LogManager.getLogger(S3BucketService.class);

    public S3BucketService()
    {
        this.transferManager=TransferManagerBuilder.defaultTransferManager();
        this.objectMapper=new ObjectMapper();
        this.amazonS3= AmazonS3ClientBuilder.defaultClient();
    }

    @Override
    public <T> T putObjectToS3(String objectKey,String bucketName,T content,String contentType) throws HttpException
    {
        try
        {
            String jsonContent=objectMapper.writeValueAsString(content);
            ByteArrayInputStream byteArrayInputStream =new ByteArrayInputStream(jsonContent.getBytes());

            ObjectMetadata objectMetadata =new ObjectMetadata();
            objectMetadata.setContentType(contentType);

            PutObjectRequest putObjectRequest =new PutObjectRequest(bucketName, objectKey,byteArrayInputStream,objectMetadata);

            Upload upload=transferManager.upload(putObjectRequest);
            upload.waitForUploadResult();
        }
        catch (InterruptedException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"InterruptedException occured",exception.getCause());
        }
        catch (JsonProcessingException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"JsonProcessingException occured",exception.getCause());
        }
        catch (AmazonServiceException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException();
        }
        catch (SdkClientException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"SdkClientException occured",exception.getCause());
        }
        return content;
    }

    @Override
    public <T> T readObjectFromS3(String objectkey, String bucketName,Class<T> type) throws HttpException
    {
        T jsonContent =null;
        try
        {
            GetObjectRequest getObjectRequest =new GetObjectRequest(bucketName,objectkey);
            S3Object s3Object=amazonS3.getObject(getObjectRequest);
            StringWriter stringWriter =new StringWriter();
            IOUtils.copy(s3Object.getObjectContent(),stringWriter);
            jsonContent=objectMapper.readValue(stringWriter.toString(),type);
        }
        catch (IOException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Unable to convert json object",exception.getCause());
        }
        catch (AmazonServiceException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"AmazonService exception",exception.getCause());
        }
        catch (SdkClientException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Client sdk exception",exception.getCause());
        }
        return jsonContent;
    }
}
