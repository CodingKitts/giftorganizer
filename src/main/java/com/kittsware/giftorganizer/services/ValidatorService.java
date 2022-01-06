package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;
import com.kittsware.giftorganizer.exceptions.UserNotFoundException;

public interface ValidatorService {
    boolean areFriends(String ownerEmail, String friendEmail);
    void isValidEmail(String email) throws InvalidEmailException, UserNotFoundException;
    boolean isItemOwner(String ownerEmail, Long giftItemId);
    boolean isValidNewItem(String ownerEmail, GiftItem item);
    boolean isValidFriendship(String recipientEmail, Long friendshipId);
}
