package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.Friendship;
import com.kittsware.giftorganizer.exceptions.FriendshipConflictException;
import com.kittsware.giftorganizer.exceptions.InvalidEmailException;
import com.kittsware.giftorganizer.exceptions.InvalidFriendshipException;
import com.kittsware.giftorganizer.exceptions.UserNotFoundException;
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

    /* ADMIN METHODS */
    @GetMapping("/admin/friends")
    public List<Friendship> getAllFriendships() {
        return this.friendshipService.getAllFriends();
    }

    /* GET METHODS */
    @GetMapping("/friends")
    public ResponseEntity<List<Friendship>> getAllFriendshipsForOwner(Principal principal) {
        try {
            List<Friendship> requests = this.friendshipService.getAllFriendsForOwner(principal.getName());
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getClass().equals(InvalidEmailException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/friends/requests")
    public ResponseEntity<List<Friendship>> getOutstandingRequests(Principal principal) {
        try {
            List<Friendship> requests = this.friendshipService.getOutstandingRequests(principal.getName());
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (RuntimeException e) {
            //If Invalid Email return Bad Request, else return Not Found for User not Found.
            if (e.getClass().equals(InvalidEmailException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/friends/sent")
    public ResponseEntity<List<Friendship>> getSentRequests(Principal principal) {
        try {
            List<Friendship> requests = this.friendshipService.getSentRequests(principal.getName());
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getClass().equals(InvalidEmailException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* POST METHODS */
    @PostMapping("/friend")
    public ResponseEntity<Friendship> createFriendship(@RequestBody String recipientEmail, Principal principal) {
        try {
            Friendship friendship = this.friendshipService.createFriendship(principal.getName(), recipientEmail);
            return new ResponseEntity<>(friendship, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getClass().equals(InvalidEmailException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (e.getClass().equals(UserNotFoundException.class)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            /*if (e.getClass().equals(FriendshipConflictException.class)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }*/
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /* PUT METHODS */
    @PutMapping("/friend/{friendshipId}")
    public ResponseEntity<Friendship> acceptFriendship(@PathVariable Long friendshipId, Principal principal) {
        try {
            Friendship friendship = this.friendshipService.acceptFriendship(principal.getName(), friendshipId);
            return new ResponseEntity<>(friendship, HttpStatus.OK);
        } catch (RuntimeException e) {
            //TODO: Refactor this so that the returned exceptions can provide the client more information. Right now client
            //      only knows if it was a bad request or not found. But now the bad request / not found could be
            //      friendship or user.
            if (e.getClass().equals(InvalidEmailException.class) || e.getClass().equals(InvalidFriendshipException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* DELETE METHODS */
    @DeleteMapping("/friend")
    public ResponseEntity<String> deleteFriendshipAsOwner(@RequestBody String friendEmail, Principal principal) {
        //TODO: Incorporate a Wish Item Service here to update purchased Items.
        
        //We should send a warning to the user being deleted that they shoudl return any items.

        //Okay so you need to:
        //See if a Friendship exists between the users.
        //Delete the Friendship Row regardless of the Friendship Owner.
        //Update Owner Items that were bought by the removed Friend to be returned.
        //Update Friend Items that were bought by the Owner to be returned.

        //So if I delete a friendship I should remove all bought Items from that friendship. Or I should have a field
        //for items that were bought but the friendship was deleted before the item was returned.
        try {
            this.friendshipService.deleteFriendship(principal.getName(), friendEmail);
            return new ResponseEntity<>("Success. Friendship deleted.", HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getClass().equals(FriendshipConflictException.class) || e.getClass().equals(InvalidEmailException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/friend/{friendshipId}")
    public ResponseEntity<String> declineFriendshipRequest(@PathVariable Long friendshipId, Principal principal) {
        try {
            this.friendshipService.declineFriendship(principal.getName(), friendshipId);
            return new ResponseEntity<>("Success. Friendship declined.", HttpStatus.OK);
        } catch (RuntimeException e) {
            //TODO: Refactor this so that the returned exceptions can provide the client more information. This is possible
            //      because you are returning a String now, and not an Object.
            if (e.getClass().equals(InvalidEmailException.class) || e.getClass().equals(InvalidFriendshipException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
