package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.GiftItem;
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
    public boolean areFriends(String ownerEmail, String friendEmail) {
        return this.friendshipRepository.existsFriendshipByOwnerEmailAndRequestedFriendEmail(ownerEmail, friendEmail);
    }

    @Override
    public boolean isValidEmail(String email) {
        //12.8.21: Right now we are only checking if there is a User associated with the given email.
        return this.userRepository.existsByUserEmail(email);
    }

    @Override
    public boolean isItemOwner(String ownerEmail, Long giftItemId) {
        GiftItem giftItem = this.giftItemRepository.findById(giftItemId).orElse(null);
        if (giftItem != null && giftItem.getOwnerEmail().equals(ownerEmail)) {
            return true;
        }
        return false;
    }
}
