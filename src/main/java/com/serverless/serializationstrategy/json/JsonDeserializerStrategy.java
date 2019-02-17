package com.serverless.serializationstrategy.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.exceptions.InternalServerErrorException;
import com.serverless.serializationstrategy.DeserializerStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;


public class JsonDeserializerStrategy implements DeserializerStrategy
{
    private ObjectMapper objectMapper;
    private static final Logger LOG = LogManager.getLogger(JsonSerializerStrategy.class);

    public JsonDeserializerStrategy()
    {
        objectMapper=new ObjectMapper();
    }

    @Override
    public <T> T  deserialize(String content, Class<T> entityClass) throws InternalServerErrorException
    {
        T deserializeEntity=null;
        try
        {
            deserializeEntity=objectMapper.readValue(content,entityClass);
        }
        catch (IOException e) {
            LOG.error(String.format("Internal server error {0}",e.getStackTrace()));
            throw new InternalServerErrorException(e.getMessage());
        }
        return deserializeEntity;
    }
}
