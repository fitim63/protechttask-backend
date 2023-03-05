package com.protecht.service;

import com.protecht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.protecht.domain.User;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id);
    }

    public User getByUsername(String u) {
        return userRepository.findByUsername(u);
    }

    public void save(User u) {
        User user = new User();
        user.setFirstname(u.getFirstname());
        user.setLastname(u.getLastname());
        user.setPassword(u.getPassword());
        user.setUsername(u.getUsername());
        userRepository.save(user);
    }

}

