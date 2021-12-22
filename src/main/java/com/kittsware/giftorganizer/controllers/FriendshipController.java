package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.services.FriendshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public List<Friendship> getAllFriendshipsForOwner(Principal principal) {
        return this.friendshipService.getAllFriendsForOwner(principal.getName());
    }

    @PostMapping("/friend")
    public ResponseEntity<Friendship> createFriendship(@RequestBody String recipientEmail, Principal principal) {
        Friendship friendship = this.friendshipService.createFriendship(principal.getName(), recipientEmail);

        //TODO: Refactor to leverage the different cases between an invalid email, friend not found, and success
        if (friendship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friendship, HttpStatus.OK);
    }

    @DeleteMapping("/friend")
    public boolean deleteFriendship(@RequestBody String friendEmail, Principal principal) {
        return this.friendshipService.deleteFriendship(principal.getName(), friendEmail);
    }
}
