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

        return this.friendshipRepository.save(new Friendship(senderEmail, recipientEmail));
    }

    @Override
    @Transactional
    public boolean deleteFriendship(String senderEmail, String recipientEmail) {
        return this.friendshipRepository.deleteFriendshipBySenderEmailAndRecipientEmail(senderEmail, recipientEmail) == 1;
    }

    @Override
    public List<Friendship> getAllFriendsForOwner(String senderEmail) {
        if (senderEmail == null) {
            return null;
        }

        return this.friendshipRepository.findFriendshipsBySenderEmail(senderEmail);
    }

    @Override
    @Transactional
    public boolean declineFriendship(String recipientEmail, Long friendshipId) {
        //Make sure RecipientEmail is valid.
        //Make sure there is a friendship associated with the ID
        //Make sure that the Recipient emails match
        //Delete the Friendship
        this.validatorService.isValidEmail(recipientEmail);

        if (!this.validatorService.isValidFriendship(recipientEmail, friendshipId)) {
            return false;
        }

        this.friendshipRepository.deleteById(friendshipId);

        return true;
    }

    //TODO: EXCEPTION HANDLING WILL OCCUR IN THE CONTROLLER
    @Override
    public Friendship acceptFriendship(String recipientEmail, Long friendshipId) {
        //TODO: Refactor this to do more than just return null.

        this.validatorService.isValidEmail(recipientEmail);

        if (!this.validatorService.isValidFriendship(recipientEmail, friendshipId)) {
            return null;
        }

        return this.friendshipRepository.acceptFriendshipRequest(friendshipId);
    }

    @Override
    public List<Friendship> getOutstandingRequests(String recipientEmail) {
        /*
            This function is supposed to validate the incoming email is valid.
            It is supposed to gather all requests that have been sent to recipientEmail that Recipient hasn't
            responded to yet. I think a custom Query will work best.
         */

        this.validatorService.isValidEmail(recipientEmail);

        //return this.friendshipRepository.findFriendshipsByRecipientEmail(recipientEmail);
        return this.friendshipRepository.findFriendshipsByRecipientEmailAndIsAcceptedFalse(recipientEmail);
    }

    @Override
    public List<Friendship> getSentRequests(String senderEmail) {
        return null;
    }
}
