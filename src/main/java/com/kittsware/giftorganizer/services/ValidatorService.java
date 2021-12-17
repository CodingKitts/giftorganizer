package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;

public interface ValidatorService {
    boolean areFriends(String ownerEmail, String friendEmail);
    boolean isValidEmail(String email);
    boolean isItemOwner(String ownerEmail, Long giftItemId);
    boolean isValidNewItem(String ownerEmail, GiftItem item);
}
