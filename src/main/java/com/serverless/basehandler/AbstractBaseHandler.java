package com.serverless.basehandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.exceptions.HttpException;
import com.serverless.request.ApiIntegrationRequest;

import java.util.Optional;

public abstract class AbstractBaseHandler implements RequestHandler<ApiIntegrationRequest, ApiGatewayResponse>
{
    @Override
    public ApiGatewayResponse handleRequest(ApiIntegrationRequest input, Context context)
    {
        ApiGatewayResponse response =null;
        try
        {
             response = doProcess(input,context);
        }
        catch(HttpException httpException)
        {

        }
        catch(Exception exception)
        {

        }
        return response;
    }

    public abstract ApiGatewayResponse doProcess(ApiIntegrationRequest request,Context context) throws HttpException;

    private <T> T ConvertApiIntegrationRequest(ApiIntegrationRequest request)
    {
        return (T)Optional.of(request);
    }
}
