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
        //The Add Friend service function now throws an exception. Otherwise if no User is found it returns false.
        try {
            if (this.validatorService.isValidEmail(recipientEmail)) {
                return this.friendshipRepository.save(new Friendship(senderEmail, recipientEmail));
            } else {
                //TODO: refactor this so that you are doing something because no User is associated with SenderEmail.
                return null;
            }
        } catch (InvalidEmailException e) {
            //TODO: refactor this so that you are doing something because the email is invalid.
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteFriendship(String senderEmail, String recipientEmail) {
        return this.friendshipRepository.deleteFriendshipBySenderEmailAndRecipientEmail(senderEmail, recipientEmail) == 1;
    }

    @Override
    public List<Friendship> getAllFriendsForOwner(String senderEmail) {
        return this.friendshipRepository.findFriendshipsBySenderEmail(senderEmail);
    }
}
