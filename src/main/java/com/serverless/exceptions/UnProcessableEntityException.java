package com.serverless.exceptions;

public class UnProcessableEntityException extends HttpException
{

    public UnProcessableEntityException(String message) {
        super(message);
    }
}
