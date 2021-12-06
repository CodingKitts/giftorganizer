package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.User;
import com.kittsware.giftorganizer.repos.UserRepository;
import com.kittsware.giftorganizer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        //Make sure the incoming object doesn't have an ID already.
        //Make sure the incoming object's email doesn't already exist.
        if (user.getUserId()!=null || doesUserEmailExist(user.getUserEmail())) {
            return null;
        }

        //12.6.21: If you try to save a User with an existing email, a 500 response is thrown
        return this.userRepository.save(user);
    }

    @Override
    public boolean updateUserName(String userEmail, String userName) {
        return false;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return null;
    }

    @Override
    public boolean deleteUserByEmail(String userEmail) {
        return false;
    }

    private boolean doesUserEmailExist(String userEmail) {
        return this.userRepository.existsByUserEmail(userEmail);
    }
}
