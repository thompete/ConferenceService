package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.error.exception.UserConflictException;
import com.thompete.conferenceservice.error.exception.UserNotFoundException;
import com.thompete.conferenceservice.model.User;
import com.thompete.conferenceservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findById(user.getLogin()).isPresent()) {
            throw new UserConflictException("Podany login jest już zajęty: " + user.getLogin());
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserConflictException("Podany email jest już zajęty: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> dbUser = userRepository.findById(user.getLogin());
        if (dbUser.isEmpty()) {
            throw new UserNotFoundException("Nie znaleziono użytkownika: " + user.getLogin());
        } else {
            dbUser = userRepository.findByEmail(user.getEmail());
            if (dbUser.isPresent()) {
                throw new UserConflictException("Podany email jest już zajęty: " + user.getEmail());
            }
            return userRepository.save(user);
        }
    }
}
