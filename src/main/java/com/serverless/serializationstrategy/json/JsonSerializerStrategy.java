package com.serverless.serializationstrategy.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.serverless.exceptions.UnProcessableEntityException;
import com.serverless.serializationstrategy.SerializerStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonSerializerStrategy implements SerializerStrategy
{
    private ObjectMapper objectMapper;
    private static final Logger LOG = LogManager.getLogger(JsonSerializerStrategy.class);

    public JsonSerializerStrategy()
    {
        objectMapper=new ObjectMapper();
    }

    @Override
    public String serialize(Object entity) throws UnProcessableEntityException
    {
        String json=null;
        try
        {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
            json= objectMapper.writeValueAsString(entity);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            LOG.debug(e.getMessage());
            throw new UnProcessableEntityException("");
        }
        return json;
    }
}
