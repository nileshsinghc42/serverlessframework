package com.serverless.service;

import com.serverless.request.CreateShipmentRequest;
import com.serverless.response.CreateShipmentResponse;

public interface ICreateShipmentService
{
    CreateShipmentResponse putCreateShipmentRequest(CreateShipmentRequest request);
}
