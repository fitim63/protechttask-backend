package com.protecht.web.controllers;

import com.protecht.domain.User;
import com.protecht.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        List<User> allUsers = userService.getAllUsers();
        logger.info("logging user: {}", user.getUsername());
        logger.info("allUsers: {}", allUsers);

        for (User u : allUsers) {
            if (user.getUsername().equals(u.getUsername()) &&
                    user.getPassword().equals(u.getPassword())) {
                return new ResponseEntity<>(u, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("Creating user: {}", user);
        if (userService.getByUsername(user.getUsername()) != null) {
            logger.info("User exists or null");
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
