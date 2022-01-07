package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;
import com.kittsware.giftorganizer.repos.FriendshipRepository;
import com.kittsware.giftorganizer.services.FriendshipService;
import com.kittsware.giftorganizer.services.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Friendship createFriendship(String senderEmail, String recipientEmail) {
        this.validatorService.isValidEmail(recipientEmail);
        this.validatorService.isValidEmail(senderEmail);
        this.validatorService.friendshipExists(senderEmail, recipientEmail);

        return this.friendshipRepository.save(new Friendship(senderEmail, recipientEmail));
    }

    @Override
    @Transactional
    public boolean deleteFriendship(String senderEmail, String recipientEmail) {
        return this.friendshipRepository.deleteFriendshipBySenderEmailAndRecipientEmail(senderEmail, recipientEmail) == 1;
    }

    @Override
    public List<Friendship> getAllFriendsForOwner(String ownerEmail) {
        this.validatorService.isValidEmail(ownerEmail);

        return this.friendshipRepository.findFriendshipsBySenderEmailAndAcceptedTrueOrRecipientEmailAndAcceptedTrue(ownerEmail, ownerEmail);
    }

    @Override
    @Transactional
    public void declineFriendship(String recipientEmail, Long friendshipId) {
        this.validatorService.isValidEmail(recipientEmail);
        this.validatorService.isValidFriendship(recipientEmail, friendshipId);
        //Should I make sure that the accepted value is false?

        this.friendshipRepository.deleteById(friendshipId);
    }

    @Override
    public Friendship acceptFriendship(String recipientEmail, Long friendshipId) {
        this.validatorService.isValidEmail(recipientEmail);
        this.validatorService.isValidFriendship(recipientEmail, friendshipId);

        return this.friendshipRepository.acceptFriendshipRequest(friendshipId);
    }

    @Override
    public List<Friendship> getOutstandingRequests(String recipientEmail) {
        this.validatorService.isValidEmail(recipientEmail);

        return this.friendshipRepository.findFriendshipsByRecipientEmailAndAcceptedFalse(recipientEmail);
    }

    @Override
    public List<Friendship> getSentRequests(String senderEmail) {
        this.validatorService.isValidEmail(senderEmail);

        return this.friendshipRepository.findFriendshipsBySenderEmailAndAcceptedFalse(senderEmail);
    }
}
