package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;
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

    /*
        1) Friend A submits request to add Friend B
        2) Friend B accepts or declines request from Friend A

        If a request exists, and the accepted value is still false, the requested friend has not addressed the request
        If someone declines a request, we must delete the entity because otherwise our app will think the request hasnt
        been addressed yet.
     */

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/admin/friends")
    public List<Friendship> getAllFriendships() {
        return this.friendshipService.getAllFriends();
    }

    @GetMapping("/friends")
    public ResponseEntity<List<Friendship>> getAllFriendshipsForOwner(Principal principal) {
        List<Friendship> friends = this.friendshipService.getAllFriendsForOwner(principal.getName());
        if (friends == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping("/friend")
    public ResponseEntity<Friendship> createFriendship(@RequestBody String recipientEmail, Principal principal) throws InvalidEmailException {
        try {
            Friendship friendship = this.friendshipService.createFriendship(principal.getName(), recipientEmail);
            if (friendship == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(friendship, HttpStatus.OK);
        } catch (InvalidEmailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: Create a function to get all friendship requests for me that havent been responded to yet.

    @PutMapping("/friend/{friendshipId}")
    public ResponseEntity<Friendship> updateFriendship(@PathVariable Long friendshipId, Principal principal) {
        //Okay so if a Friend declines a request, we shouldnt even update, we should just delete.
        //Thus if we are in this function, that means the request was accepted. So we just need to update the one
        //column.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/friend")
    public boolean deleteFriendshipAsOwner(@RequestBody String friendEmail, Principal principal) {
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

    //Remember, only the recipient of the Request can decline. Otherwise it is a normal delete.
    @DeleteMapping("/friend/{friendshipId}")
    public boolean declineFriendshipRequest(@PathVariable Long friendshipId, Principal principal) {
        //TODO: Write this function.
        return this.friendshipService.declineFriendship(principal.getName(), friendshipId);
    }
}
