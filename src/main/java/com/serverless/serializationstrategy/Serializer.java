package com.serverless.serializationstrategy;

import com.serverless.serializationstrategy.json.JsonSerializerStrategy;
import org.apache.http.entity.ContentType;

import java.util.Optional;
import java.util.stream.Stream;

public enum Serializer
{
    SERIALIZER(ContentType.APPLICATION_JSON.getMimeType(),new JsonSerializerStrategy());
    private String contentType;
    private SerializerStrategy serializerStrategy;

    Serializer(String contentType,SerializerStrategy serializerStrategy)
    {
        this.contentType=contentType;
        this.serializerStrategy=serializerStrategy;
    }

    public String getContentType()
    {
        return this.contentType;
    }

    public SerializerStrategy getDeserializerStrategy()
    {
        return this.serializerStrategy;
    }

    public static Optional<Serializer> strategy(String contentType)
    {
        return Stream.of(Serializer.values()).
                filter(e->e.getContentType().equals(contentType)).findFirst();
    }
}
