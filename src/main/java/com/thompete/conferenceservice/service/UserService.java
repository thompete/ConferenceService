package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user);
}
