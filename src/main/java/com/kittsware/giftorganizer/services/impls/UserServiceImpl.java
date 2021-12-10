package com.kittsware.giftorganizer.services.impls;

import com.kittsware.giftorganizer.entities.User;
import com.kittsware.giftorganizer.repos.UserRepository;
import com.kittsware.giftorganizer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(User user) {
        logger.info("USER NAME: " + user.getUserName());

        //Make sure the incoming object doesn't have an ID already.
        //Make sure the incoming object's email doesn't already exist.
        if (user.getUserId() != null || doesUserEmailExist(user.getUserEmail())) {
            //TODO: Split these checks into separate checks each returning something other than null.
            return null;
        }

        //12.6.21: If you try to save a User with an existing email, a 500 response is thrown
        if (this.userRepository.findUserByUserEmail(user.getUserEmail()).isPresent()) {
            //If the User Repository finds someone with the same Username, that means we need to have them change the new username
            //TODO: Update this to return something other than null.
            return null;
        }
        User tmpUser = new User();
        tmpUser.setUserName(user.getUserName());
        tmpUser.setUserEmail(user.getUserEmail());
        tmpUser.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        return this.userRepository.save(tmpUser);

    }

    @Override
    @Transactional
    public boolean updateUserName(String userEmail, String userName) {
        //TODO: Do something if anything other than 1 row was updated.
        //TODO: Check that the userEmail is the email from the Principal user.
        return this.userRepository.updateUserName(userName, userEmail) == 1;
    }

    @Override
    @Transactional
    public boolean deleteUserByEmail(String userEmail) {
        //TODO: Check that the userEmail is the email from the Principal user.

        //TODO: Update the return values so better information is conveyed.

        if (this.userRepository.findUserByUserEmail(userEmail).isPresent()) {
            return this.userRepository.deleteUserByUserEmail(userEmail) == 1;
        }
        return false;
    }

    private boolean doesUserEmailExist(String userEmail) {
        return this.userRepository.existsByUserEmail(userEmail);
    }
}
