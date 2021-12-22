package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;

public interface ValidatorService {
    boolean areFriends(String ownerEmail, String friendEmail);
    boolean isValidEmail(String email) throws InvalidEmailException;
    boolean isItemOwner(String ownerEmail, Long giftItemId);
    boolean isValidNewItem(String ownerEmail, GiftItem item);
}
