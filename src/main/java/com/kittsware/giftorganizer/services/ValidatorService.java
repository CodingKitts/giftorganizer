package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.exceptions.*;

public interface ValidatorService {
    void friendshipExists(String ownerEmail, String friendEmail) throws FriendshipConflictException;
    void isValidEmail(String email) throws InvalidEmailException, UserNotFoundException;
    boolean isItemOwner(String ownerEmail, Long giftItemId);
    boolean isValidNewItem(String ownerEmail, GiftItem item);
    void isValidFriendship(String recipientEmail, Long friendshipId) throws InvalidFriendshipException, FriendshipNotFoundException;
}
