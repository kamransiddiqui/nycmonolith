package com.example.nycappmonolith.controllers;

import com.example.nycappmonolith.models.User;
import com.example.nycappmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/users/{userId}")
    public Optional<User> findUserById(@PathVariable Long userId) throws NotFoundException {
        Optional<User> foundUser = userRepository.findById(userId);

        if (foundUser == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        return foundUser;
    }

    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PatchMapping("/users/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) {

        User userFromDb = userRepository.findById(userId).get();

        userFromDb.setUserName(userRequest.getUserName());
        userFromDb.setFirstName(userRequest.getFirstName());
        userFromDb.setLastName(userRequest.getLastName());
        userFromDb.setLastName(userRequest.getEmail());
        userFromDb.setLastName(userRequest.getJob());

        return userRepository.save(userFromDb);
    }

}
