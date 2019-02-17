package com.serverless.serializationstrategy;

import com.serverless.serializationstrategy.json.JsonDeserializerStrategy;
import org.apache.http.entity.ContentType;

import java.util.Optional;
import java.util.stream.Stream;

public enum Deserializer
{
    DESERIALIZER(ContentType.APPLICATION_JSON.getMimeType(),new JsonDeserializerStrategy());

    private String contentType;
    private DeserializerStrategy deserializerStrategy;
    Deserializer(String contentType,DeserializerStrategy deserializerStrategy)
    {
        this.contentType=contentType;
        this.deserializerStrategy=deserializerStrategy;
    }

    public String getContentType()
    {
        return this.contentType;
    }

    public DeserializerStrategy getDeserializerStrategy()
    {
        return this.deserializerStrategy;
    }

    public static Optional<Deserializer> strategy(String contentType)
    {
        return Stream.of(Deserializer.values()).
                filter(e->e.getContentType().equals(contentType)).findFirst();
    }
}
