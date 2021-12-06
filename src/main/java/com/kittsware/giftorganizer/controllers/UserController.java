package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.User;
import com.kittsware.giftorganizer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PutMapping("/user/update")
    public boolean updateUserName(@RequestBody User user) {
        //TODO: When you add Spring Security, replace user.getUserEmail() with Principal.GetName().
        return this.userService.updateUserName(user.getUserEmail(), user.getUserName());
    }
}
