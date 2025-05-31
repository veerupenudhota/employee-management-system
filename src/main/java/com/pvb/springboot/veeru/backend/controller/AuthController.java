package com.pvb.springboot.veeru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.pvb.springboot.veeru.backend.entity.User;
import com.pvb.springboot.veeru.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        System.out.println("Login attempt for user: " + loginRequest.getUsername());

        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user == null) {
            System.out.println("User not found in DB");
            return ResponseEntity.status(401).body("{\"message\": \"User not found\"}");
        }

        System.out.println("Raw password: " + loginRequest.getPassword());
        System.out.println("Encoded password from DB: " + user.getPassword());

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("Login successful!");
            return ResponseEntity.ok("{\"token\": \"dummy-jwt-token\"}");
        } else {
            System.out.println("Password mismatch");
            return ResponseEntity.status(401).body("{\"message\": \"Invalid password\"}");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("{\"message\": \"Username already exists\"}");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("{\"message\": \"User registered successfully\"}");
    }
}
