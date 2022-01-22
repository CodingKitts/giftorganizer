package com.kittsware.giftorganizer.exceptions;

public class InvalidOwnerException extends RuntimeException{
    private String message;

    public InvalidOwnerException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidOwnerException() {}
}
