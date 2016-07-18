package com.conflux.finflux.util.exception;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class CustomException extends RuntimeException{
    private final String resource;
    private final String message;

    public CustomException(String resource, String message) {
        super(message);
        this.resource = resource;
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
