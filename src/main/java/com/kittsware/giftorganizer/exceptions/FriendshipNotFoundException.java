package com.kittsware.giftorganizer.exceptions;

public class FriendshipNotFoundException extends RuntimeException{
    private String message;

    public FriendshipNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public FriendshipNotFoundException() {}
}
