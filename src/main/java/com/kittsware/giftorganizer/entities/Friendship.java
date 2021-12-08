package com.kittsware.giftorganizer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    private String ownerEmail;
    private String requestedFriendEmail;

    //TODO: Incorporate more details into this like date, and make it a request feature.

    public Friendship() {}

    public Friendship(String ownerEmail, String requestedFriendEmail) {
        this.ownerEmail = ownerEmail;
        this.requestedFriendEmail = requestedFriendEmail;
    }

    public Long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String listOwnerId) {
        this.ownerEmail = listOwnerId;
    }

    public String getRequestedFriendEmail() {
        return requestedFriendEmail;
    }

    public void setRequestedFriendEmail(String requestedFriendId) {
        this.requestedFriendEmail = requestedFriendId;
    }
}
