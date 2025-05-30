package com.ams.dev.api.parking.exception;

public class CannotPermissionException extends Exception{

    public CannotPermissionException() {
        super();
    }

    public CannotPermissionException(String message) {
        super(message);
    }

    public CannotPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
