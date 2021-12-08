package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.services.FriendshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendshipController {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipController.class);
    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    //TODO: Do I need a web controller method for checking if two users are friends? No I think I only need that functionality when validating stuff.

    @GetMapping("/admin/friends")
    public List<Friendship> getAllFriendships() {
        return this.friendshipService.getAllFriends();
    }

    @GetMapping("/friends")
    public List<Friendship> getAllFriendshipsForOwner(@RequestBody String ownerEmail) {
        //TODO: Replace RequestBody OwnerEmail with Spring Security Principal.
        return this.friendshipService.getAllFriendsForOwner(ownerEmail);
    }

    @PostMapping("/friend")
    public Friendship createFriendship(@RequestBody String friendshipEmail) {
        //TODO: Replace my temp email with Spring Security Principal.
        String ownerEmail = "user@user.com";
        return this.friendshipService.createFriendship(ownerEmail, friendshipEmail);
    }

    @DeleteMapping("/friend")
    public boolean deleteFriendship(@RequestBody String friendEmail) {
        //TODO: Replace my temp email with Spring Security Principal.
        String ownerEmail = "user@user.com";
        return this.friendshipService.deleteFriendship(ownerEmail, friendEmail);
    }
}
