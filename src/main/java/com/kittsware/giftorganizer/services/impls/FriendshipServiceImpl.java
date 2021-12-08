package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.repos.FriendshipRepository;
import com.kittsware.giftorganizer.services.FriendshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);
    private final FriendshipRepository friendshipRepository;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public List<Friendship> getAllFriends() {
        return this.friendshipRepository.findAll();
    }

    @Override
    public Friendship createFriendship(String ownerEmail, String requestedFriendEmail) {
        //TODO: Validate that a DB ID gets created.
        return this.friendshipRepository.save(new Friendship(ownerEmail, requestedFriendEmail));
    }

    @Override
    public boolean deleteFriendship(String ownerEmail, String deletedFriendEmail) {

        return this.friendshipRepository.deleteFriendshipByOwnerEmailAndRequestedFriendEmail(ownerEmail, deletedFriendEmail) == 1;
    }

    @Override
    public List<Friendship> getAllFriendsForOwner(String ownerEmail) {
        return this.friendshipRepository.findFriendshipsByOwnerEmail(ownerEmail);
    }
}
