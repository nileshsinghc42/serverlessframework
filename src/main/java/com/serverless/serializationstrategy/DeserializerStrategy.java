package com.serverless.serializationstrategy;

import com.serverless.exceptions.InternalServerErrorException;

public interface DeserializerStrategy
{
    <T> T deserialize(String content, Class<T> entityClass) throws InternalServerErrorException;
}
