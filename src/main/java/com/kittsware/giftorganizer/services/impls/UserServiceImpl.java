package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.models.User;
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
        //TODO: What do we need to validate before saving a new User?
        //Validate that the incoming User email doesn't already exist. (I am curious what Spring returns)
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
}
