package com.serverless.basehandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.exceptions.BadRequestException;
import com.serverless.exceptions.HttpException;
import com.serverless.request.ApiGatewayRequest;
import com.serverless.response.ApiGatewayResponse;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.serializationstrategy.Deserializer;
import com.serverless.serializationstrategy.DeserializerStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;

public abstract class AbstractBaseHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse>
{
    private static final Logger LOG= LogManager.getLogger(AbstractBaseHandler.class);

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context)
    {
        ApiGatewayResponse response =null;
        try
        {
             response = doProcess(input,context);
        }
        catch(HttpException httpException)
        {
            LOG.error(String.format("HttpException occured {0} {1}",
                                 httpException.getMessage(),httpException.getStackTrace()));
        }
        catch(Exception exception)
        {
            LOG.error(String.format("Internal Server Error occured {0}",exception.getStackTrace()));
        }
        return response;
    }

    public abstract ApiGatewayResponse doProcess(ApiGatewayRequest request,Context context) throws HttpException;

    public static <T extends Object> T ConvertApiIntegrationRequest(ApiGatewayRequest request,T type)
    {

        Deserializer deserializer = Deserializer.strategy(request.getContentType())
                .orElseThrow(()->new BadRequestException("Content type not supported"));

        DeserializerStrategy deserializerStrategy=deserializer.getDeserializerStrategy();

        if(type instanceof Object && type instanceof Serializable)
        {
            return (T)deserializerStrategy.deserialize(request.getBody(),type.getClass());
        }

        return null;
    }
}
