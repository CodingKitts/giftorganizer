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

    private Long listOwnerId;
    private Long requestedFriendId;

    //TODO: Incorporate more details into this like date, and make it a request feature.

    public Friendship() {}

    public Long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public Long getListOwnerId() {
        return listOwnerId;
    }

    public void setListOwnerId(Long listOwnerId) {
        this.listOwnerId = listOwnerId;
    }

    public Long getRequestedFriendId() {
        return requestedFriendId;
    }

    public void setRequestedFriendId(Long requestedFriendId) {
        this.requestedFriendId = requestedFriendId;
    }
}
