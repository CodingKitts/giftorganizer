package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.Friendship;

import java.util.List;

public interface FriendshipService {
    Friendship createFriendship(String senderEmail, String recipientEmail);
    boolean deleteFriendship(String ownerEmail, String deletedFriendEmail);
    boolean declineFriendship(String recipientEmail, Long friendshipId);
    List<Friendship> getAllFriendsForOwner(String ownerEmail);

    //ADMIN
    List<Friendship> getAllFriends();
}
