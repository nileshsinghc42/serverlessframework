package com.serverless.delegate;

import com.serverless.response.CreateShipmentResponse;

public class CreateShipmentDelegate
{
    public static CreateShipmentResponse Delegate()
    {
        return new CreateShipmentResponse();
    }
}
