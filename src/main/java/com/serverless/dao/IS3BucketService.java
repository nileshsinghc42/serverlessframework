package com.serverless.dao;

import com.serverless.exceptions.HttpException;

public interface IS3BucketService
{
    <T> T putObjectToS3(String objectKey,String bucketName,T content,String contentType) throws HttpException;
    <T> T readObjectFromS3(String objectkey,String bucketName) throws HttpException;
}
