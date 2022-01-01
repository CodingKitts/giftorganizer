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
    public ResponseEntity<List<Friendship>> getAllFriendshipsForOwner(Principal principal) {
        return new ResponseEntity<>(this.friendshipService.getAllFriendsForOwner(principal.getName()), HttpStatus.OK);
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
        //Okay so someone wants to delete a friend from their list. What exactly does this mean?
        //Make sure that a Friendship exists, delete it. What about any associated items?
        //Do we assume a bought item will be returned? Or still given? I am leaning on returned.

        //We should send a warning to the user being deleted that they shoudl return any items.

        //Okay so you need to:
        //See if a Friendship exists between the users.
        //Delete the Friendship Row regardless of the Friendship Owner.
        //Update Owner Items that were bought by the removed Friend to be returned.
        //Update Friend Items that were bought by the Owner to be returned.

        //So either person on the Friendship can delete it.

        //TODO: If one friend deletes a friendship, does the other person still get access to their list?


        return this.friendshipService.deleteFriendship(principal.getName(), friendEmail);
    }
}
