package com.serverless.exceptions;

import java.util.List;

public class HttpException extends Exception
{
    private String message;
    private int httpStatusCode;
    private List<String> errorMessageList;

    public HttpException(String message)
    {
        super(message);
    }
}
