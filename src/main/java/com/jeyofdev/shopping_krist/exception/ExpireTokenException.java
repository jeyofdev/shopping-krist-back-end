package com.jeyofdev.shopping_krist.exception;

public class ExpireTokenException extends RuntimeException {
    public ExpireTokenException(String message) {
        super(message);
    }

    public ExpireTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpireTokenException(Throwable cause) {
        super(cause);
    }
}
