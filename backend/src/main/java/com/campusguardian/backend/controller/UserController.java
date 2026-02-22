package com.campusguardian.backend.controller;

import com.campusguardian.backend.model.User;
import com.campusguardian.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) throws ExecutionException, InterruptedException {
        if (userService.userExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) throws ExecutionException, InterruptedException {
        if (userService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    // Get user by email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}