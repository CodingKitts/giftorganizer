package com.kittsware.giftorganizer.services;

public interface ValidatorService {
    boolean areFriends(String ownerEmail, String friendEmail);
    boolean isValidEmail(String email);
    boolean isItemOwner(String ownerEmail, Long giftItemId);
}
