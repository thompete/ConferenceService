package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUser(String login);
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user);
    boolean exists(String login);
}
