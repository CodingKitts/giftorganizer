package com.kittsware.giftorganizer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    @NotEmpty
    private String senderEmail;
    @NotEmpty
    private String recipientEmail;
    private boolean isAccepted;

    public Friendship() {
    }

    public Friendship(String senderEmail, String recipientEmail) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.isAccepted = false;
    }
}
