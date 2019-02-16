package com.serverless.serializationstrategy;

import com.serverless.exceptions.InternalServerErrorException;

import java.util.Optional;

public interface DeserializerStrategy
{
    <T> Optional<T> deserialize(String content, Class<T> entityClass) throws InternalServerErrorException;
}
