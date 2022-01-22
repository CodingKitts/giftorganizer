package com.kittsware.giftorganizer.exceptions;

public class GiftItemNotFoundException extends RuntimeException {
    private String message;

    public GiftItemNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public GiftItemNotFoundException() {}
}
