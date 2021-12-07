package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.User;
import com.kittsware.giftorganizer.repos.UserRepository;
import com.kittsware.giftorganizer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
        if (user.getUserId() != null || doesUserEmailExist(user.getUserEmail())) {
            return null;
        }

        //12.6.21: If you try to save a User with an existing email, a 500 response is thrown
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean updateUserName(String userEmail, String userName) {
        //TODO: Do something if anything other than 1 row was updated.
        //TODO: Check that the userEmail is the email from the Principal user.
        return this.userRepository.updateUserName(userName, userEmail) == 1;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        //TODO: Do more with the user being present.
        Optional<User> user = this.userRepository.findUserByUserEmail(userEmail);
        if (user.isPresent()) {
            logger.info("USER: "+user.get().getUserName());
        } else {
            logger.info("USER: "+user);
        }
        return user.orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteUserByEmail(String userEmail) {
        //TODO: Do something if the below statement returns false.
        //TODO: Check that the userEmail is the email from the Principal user.
        return this.userRepository.deleteUserByUserEmail(userEmail) == 1;
    }

    private boolean doesUserEmailExist(String userEmail) {
        return this.userRepository.existsByUserEmail(userEmail);
    }
}
