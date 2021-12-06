package com.kittsware.giftorganizer.services;

import com.kittsware.giftorganizer.entities.User;

public interface UserService {
    User createUser(User user);
    boolean updateUserName(String userEmail, String userName);
    User getUserByEmail(String userEmail);
    boolean deleteUserByEmail(String userEmail);
}
