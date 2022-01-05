package com.kittsware.giftorganizer.exceptions;

public class UserNotFoundException extends RuntimeException{
    private String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFoundException() {}
}
