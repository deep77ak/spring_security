package com.springSecurity.controller;

import com.springSecurity.entity.employeeSignup;
import com.springSecurity.repo.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class signupController {
    @Autowired
    private repository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody employeeSignup emp) {
//        emp.setPassword(encoder.encode(emp.getPassword()));
//
//     repo.save(emp);
//        return "Employee Registered";
//    }
        try {
            // Check if username already exists
            Optional<employeeSignup> existingUser = repo.findByUsername(emp.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("error", "Username '" + emp.getUsername() + "' is already taken."));
            }

            // Encode password and save user
            emp.setPassword(encoder.encode(emp.getPassword()));
            repo.save(emp);

            return ResponseEntity.ok(Map.of("message", "Employee Registered Successfully"));

        } catch (Exception e) {
            // Log error if needed, then return a generic error response
            return ResponseEntity
                    .status(500)
                    .body(Map.of("error", "An error occurred while processing your request."));
        }
    }
}

