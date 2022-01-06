package com.kittsware.giftorganizer.exceptions;

public class InvalidFriendshipException extends RuntimeException{
    private String message;

    public InvalidFriendshipException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidFriendshipException() {}
}
