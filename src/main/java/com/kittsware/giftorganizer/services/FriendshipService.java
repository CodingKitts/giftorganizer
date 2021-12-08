package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.Friendship;

import java.util.List;

public interface FriendshipService {
    Friendship createFriendship(String ownerEmail, String requestedFriendEmail);
    boolean deleteFriendship(String ownerEmail, String deletedFriendEmail);
    List<Friendship> getAllFriendsForOwner(String ownerEmail);

    //ADMIN
    List<Friendship> getAllFriends();
}
