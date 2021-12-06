package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.models.User;

public interface UserService {
    //What are the core User functions I need?
    //Create a User
    //Find a user by User Email
    //Update a User name
    //Delete a User by User Email

    User createUser(User user);
    boolean updateUserName(String userEmail, String userName);
    User getUserByEmail(String userEmail);
    boolean deleteUserByEmail(String userEmail);
}
