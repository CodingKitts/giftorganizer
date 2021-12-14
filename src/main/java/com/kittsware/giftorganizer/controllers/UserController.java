package com.kittsware.giftorganizer.controllers;

import com.kittsware.giftorganizer.entities.User;
import com.kittsware.giftorganizer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PutMapping("/user/update")
    public boolean updateUserName(@RequestBody String userName, Principal principal) {
        return this.userService.updateUserName(principal.getName(), userName);
    }

    @DeleteMapping("/user")
    public boolean deleteUser(Principal principal) {
        return this.userService.deleteUserByEmail(principal.getName());
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
