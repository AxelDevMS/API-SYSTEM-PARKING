package com.ams.dev.api.parking.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
