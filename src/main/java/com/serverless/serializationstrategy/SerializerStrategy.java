package com.serverless.serializationstrategy;

import com.serverless.exceptions.UnProcessableEntityException;

public interface SerializerStrategy
{
     String serialize(Object entity) throws UnProcessableEntityException;
}
