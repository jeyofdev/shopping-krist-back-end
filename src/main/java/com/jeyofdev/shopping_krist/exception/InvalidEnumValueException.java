package com.jeyofdev.shopping_krist.exception;

public class InvalidEnumValueException extends RuntimeException {
    public InvalidEnumValueException(String message) {
        super(message);
    }

    public InvalidEnumValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEnumValueException(Throwable cause) {
        super(cause);
    }
}
