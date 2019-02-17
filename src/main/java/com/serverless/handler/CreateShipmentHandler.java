package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.basehandler.AbstractBaseHandler;
import com.serverless.exceptions.HttpException;
import com.serverless.request.ApiGatewayRequest;
import com.serverless.request.CreateShipmentRequest;
import com.serverless.response.ApiGatewayResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateShipmentHandler extends AbstractBaseHandler
{
    private static final Logger LOG= LogManager.getLogger(CreateShipmentHandler.class);

    @Override
    public ApiGatewayResponse doProcess(ApiGatewayRequest request, Context context) throws HttpException
    {
        ApiGatewayResponse apiGatewayResponse=null;

        LOG.info(String.format("CreateShipmentRequest received from E2S :: {0}",request.getBody()));

        CreateShipmentRequest createShipmentRequest=new CreateShipmentRequest();

        createShipmentRequest=ConvertApiIntegrationRequest(request,createShipmentRequest);

        return apiGatewayResponse;
    }
}
