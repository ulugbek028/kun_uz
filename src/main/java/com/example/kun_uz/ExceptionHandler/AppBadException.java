package com.example.kun_uz.ExceptionHandler;

public class AppBadException extends RuntimeException{
    public AppBadException(String message) {
        super(message);
    }
}
