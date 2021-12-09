package com.kittsware.giftorganizer.entities.auth;

import com.kittsware.giftorganizer.entities.User;

import javax.persistence.*;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;

    public Authority() {}

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
