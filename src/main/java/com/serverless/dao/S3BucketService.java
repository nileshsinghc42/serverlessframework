package com.serverless.dao;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.exceptions.HttpException;
import com.serverless.exceptions.InternalServerErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;


public class S3BucketService implements IS3BucketService
{
    private ObjectMapper objectMapper;
    private TransferManager transferManager;
    private static final Logger LOG= LogManager.getLogger(S3BucketService.class);

    public S3BucketService()
    {
        this.transferManager=TransferManagerBuilder.defaultTransferManager();
        this.objectMapper=new ObjectMapper();
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

            transferManager.upload(putObjectRequest);
        }
        catch (JsonProcessingException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException();
        }
        catch (AmazonServiceException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException();
        }
        catch (SdkClientException exception)
        {
            LOG.error(exception.getMessage());
            throw new InternalServerErrorException();
        }
        return content;
    }

    @Override
    public <T> T readObjectFromS3(String objectkey, String bucketName) throws HttpException
    {
        return null;
    }
}
