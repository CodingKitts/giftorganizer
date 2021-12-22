package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.repos.FriendshipRepository;
import com.kittsware.giftorganizer.services.FriendshipService;
import com.kittsware.giftorganizer.services.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);
    private final FriendshipRepository friendshipRepository;
    private final ValidatorService validatorService;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, ValidatorService validatorService) {
        this.friendshipRepository = friendshipRepository;
        this.validatorService = validatorService;
    }

    //TODO: Incorporate the Validator service to check that FriendEmails actually have user's tied to them.

    @Override
    public List<Friendship> getAllFriends() {
        return this.friendshipRepository.findAll();
    }

    @Override
    public Friendship createFriendship(String ownerEmail, String requestedFriendEmail) {
        //TODO: Refactor the names of the emails to make sure they match to the Entity names
        if (!this.validatorService.isValidEmail(ownerEmail)) {
            return null;
        }
        return this.friendshipRepository.save(new Friendship(ownerEmail, requestedFriendEmail));
    }

    @Override
    @Transactional
    public boolean deleteFriendship(String ownerEmail, String deletedFriendEmail) {
        return this.friendshipRepository.deleteFriendshipByOwnerEmailAndRequestedFriendEmail(ownerEmail, deletedFriendEmail) == 1;
    }

    @Override
    public List<Friendship> getAllFriendsForOwner(String ownerEmail) {
        return this.friendshipRepository.findFriendshipsByOwnerEmail(ownerEmail);
    }
}
