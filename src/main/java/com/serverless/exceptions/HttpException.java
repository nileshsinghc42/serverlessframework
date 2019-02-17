package com.serverless.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class HttpException extends Exception
{
    private int httpStatusCode;
    private Object entity;

    public HttpException(int httpStatusCode)
    {
        super();
        this.httpStatusCode=httpStatusCode;
    }

    public HttpException(int httpStatusCode,Object entity)
    {
        super();
        this.httpStatusCode=httpStatusCode;
        this.entity=entity;
    }

    public HttpException(int httpStatusCode,final String message,final Throwable cause)
    {
        super(message);
        initCause(cause);
        this.httpStatusCode=httpStatusCode;
    }

    public HttpException(int httpStatusCode,Object entity,final String message,final Throwable cause)
    {
        super(message);
        initCause(cause);
        this.httpStatusCode=httpStatusCode;
        this.entity=entity;
    }
}
