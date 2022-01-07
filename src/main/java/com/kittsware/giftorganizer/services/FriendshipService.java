package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.Friendship;

import java.util.List;

public interface FriendshipService {
    Friendship createFriendship(String senderEmail, String recipientEmail);
    Friendship acceptFriendship(String recipientEmail, Long friendshipId);
    boolean deleteFriendship(String ownerEmail, String deletedFriendEmail);
    void declineFriendship(String recipientEmail, Long friendshipId);
    List<Friendship> getAllFriendsForOwner(String ownerEmail);
    List<Friendship> getOutstandingRequests(String recipientEmail);
    List<Friendship> getSentRequests(String senderEmail);

    //ADMIN
    List<Friendship> getAllFriends();
}
