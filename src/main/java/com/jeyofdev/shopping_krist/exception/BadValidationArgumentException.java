package com.jeyofdev.shopping_krist.exception;

public class BadValidationArgumentException extends RuntimeException {
    public BadValidationArgumentException(String message) {
        super(message);
    }

    public BadValidationArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadValidationArgumentException(Throwable cause) {
        super(cause);
    }
}
