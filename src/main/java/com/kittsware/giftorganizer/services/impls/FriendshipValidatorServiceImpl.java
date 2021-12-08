package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.repos.FriendshipRepository;
import com.kittsware.giftorganizer.services.FriendshipValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FriendshipValidatorServiceImpl implements FriendshipValidatorService {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipValidatorServiceImpl.class);
    private final FriendshipRepository friendshipRepository;

    public FriendshipValidatorServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public boolean areFriends(String ownerEmail, String friendEmail) {
        return this.friendshipRepository.existsFriendshipByOwnerEmailAndRequestedFriendEmail(ownerEmail, friendEmail);
    }
}
