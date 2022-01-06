package com.kittsware.giftorganizer.exceptions;

public class FriendshipConflictException extends RuntimeException{
    private String message;

    public FriendshipConflictException(String message) {
        super(message);
        this.message = message;
    }

    public FriendshipConflictException() {}
}
