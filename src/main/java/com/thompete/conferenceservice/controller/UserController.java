package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.model.User;
import com.thompete.conferenceservice.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    public User updateEmail(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }
}
