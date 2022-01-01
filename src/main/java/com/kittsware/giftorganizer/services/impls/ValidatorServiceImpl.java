package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.GiftItem;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;
import com.kittsware.giftorganizer.repos.FriendshipRepository;
import com.kittsware.giftorganizer.repos.GiftItemRepository;
import com.kittsware.giftorganizer.repos.UserRepository;
import com.kittsware.giftorganizer.services.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidatorServiceImpl implements ValidatorService {
    private static final Logger logger = LoggerFactory.getLogger(ValidatorServiceImpl.class);
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;
    private final GiftItemRepository giftItemRepository;

    //TODO: Brainstorm a better way to validate a user's email without using the UserRepository. I just want to disconnect the User Repo as much as possible for security purposes.

    public ValidatorServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository, GiftItemRepository giftItemRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
        this.giftItemRepository = giftItemRepository;
    }

    //TODO: Create a function that checks that the given email address is of the correct format. (REGEX FUN)

    @Override
    public boolean areFriends(String senderEmail, String recipientEmail) {
        return this.friendshipRepository.existsFriendshipBySenderEmailAndRecipientEmail(senderEmail, recipientEmail) || this.friendshipRepository.existsFriendshipBySenderEmailAndRecipientEmail(senderEmail, recipientEmail);
    }

    @Override
    public boolean isValidEmail(String email) {
        //Make sure the email isn't empty and the email matches a valid regex
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new InvalidEmailException("Invalid Email provided");
        }

        //Make sure the email has an associative User
        return this.userRepository.existsByUserEmail(email);
    }

    @Override
    public boolean isItemOwner(String ownerEmail, Long giftItemId) {
        GiftItem giftItem = this.giftItemRepository.findById(giftItemId).orElse(null);
        return giftItem != null && giftItem.getOwnerEmail().equals(ownerEmail);
    }

    @Override
    public boolean isValidNewItem(String ownerEmail, GiftItem item) {
        //TODO: Refactor to throw a Custom Exception here instead. (Malformed Request, although do we say what is wrong?
        //Check that the new item doesn't have an ID
        if (item.getGiftItemId() != null) {
            return false;
        }

        //Check that the new item doesn't have a Buyer
        //FOr some reason Buyer email is added as a String?
        if (item.getBuyerEmail() != null) {
            return false;
        }

        //Check that the new item isn't bought
        if (item.isBought()) {
            return false;
        }

        //Check the current user & item Owner are the same
        if (!item.getOwnerEmail().equals(ownerEmail)) {
            return false;
        }

        return true;
    }
}
