package com.kittsware.giftorganizer.exceptions;

public class InvalidItemException extends RuntimeException {
    private String message;

    public InvalidItemException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidItemException() {}
}
