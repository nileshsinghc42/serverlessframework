package com.serverless.service;


import com.serverless.dao.IS3BucketService;
import com.serverless.dao.S3BucketService;
import com.serverless.request.CreateShipmentRequest;
import com.serverless.response.CreateShipmentResponse;

public class CreateShipmentService implements ICreateShipmentService
{
    private IS3BucketService is3BucketService;
    private static CreateShipmentService createShipmentService;

    private CreateShipmentService()
    {
        this.is3BucketService=new S3BucketService();
    }

    public static CreateShipmentService getInstance()
    {
        if(createShipmentService==null)
            createShipmentService=new CreateShipmentService();

        return createShipmentService;
    }

    public CreateShipmentResponse putCreateShipmentRequest(CreateShipmentRequest request)
    {
        return new CreateShipmentResponse();
    }
}
