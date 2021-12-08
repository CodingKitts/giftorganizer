package com.kittsware.giftorganizer.services;

public interface FriendshipValidatorService {
    boolean areFriends(String ownerEmail, String friendEmail);
}
