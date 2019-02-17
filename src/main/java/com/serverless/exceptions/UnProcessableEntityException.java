package com.serverless.exceptions;

import org.apache.http.HttpStatus;

public class UnProcessableEntityException extends HttpException
{

    public UnProcessableEntityException()
    {
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    public UnProcessableEntityException(String message)
    {
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY,message);
    }

    public UnProcessableEntityException(Object entity,String message,Throwable cause)
    {
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY,message,cause);
    }

    public UnProcessableEntityException(String message,Throwable cause)
    {
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY,message,cause);
    }
}
