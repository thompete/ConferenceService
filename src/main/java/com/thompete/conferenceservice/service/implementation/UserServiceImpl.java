package com.thompete.conferenceservice.service.implementation;

import com.thompete.conferenceservice.error.exception.ConflictException;
import com.thompete.conferenceservice.error.exception.NotFoundException;
import com.thompete.conferenceservice.model.User;
import com.thompete.conferenceservice.repository.UserRepository;
import com.thompete.conferenceservice.service.UserService;
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
    public Optional<User> getUser(String login) {
        return userRepository.findById(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsById(user.getLogin())) {
            throw new ConflictException("Podany login jest już zajęty: " + user.getLogin());
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException("Podany email jest już zajęty: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getLogin())) {
            throw new NotFoundException("Nie znaleziono użytkownika: " + user.getLogin());
        } else {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new ConflictException("Podany email jest już zajęty: " + user.getEmail());
            }
            return userRepository.save(user);
        }
    }

    @Override
    public boolean exists(String login) {
        return userRepository.existsById(login);
    }
}
