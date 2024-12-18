package com.epic;

public class InvalidExpressionException extends RuntimeException
{
    InvalidExpressionException(String message)
    {
        super(message);
    }
}
