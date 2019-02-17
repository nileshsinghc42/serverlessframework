package com.serverless.exceptions;

import org.apache.http.HttpStatus;

public class InternalServerErrorException extends HttpException
{

    public InternalServerErrorException()
    {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Object entity)
    {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR,entity);
    }

    public InternalServerErrorException(Object entity,String message,Throwable cause)
    {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR,entity,message,cause);
    }

    public InternalServerErrorException(String message,Throwable cause)
    {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR,message,cause);
    }
}
