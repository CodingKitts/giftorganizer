package com.kittsware.giftorganizer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    private String senderEmail;
    private String recipientEmail;
    private boolean accepted;

    public Friendship() {
    }

    public Friendship(String senderEmail, String recipientEmail) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.accepted = false;
    }
}
