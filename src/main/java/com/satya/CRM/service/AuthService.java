package com.satya.CRM.service;

import com.satya.CRM.dto.AuthRequest;
import com.satya.CRM.dto.AuthResponse;
import com.satya.CRM.entity.User;
import com.satya.CRM.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public AuthResponse login(AuthRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Simple plain text password check as requested (no Spring Security)
            if (user.getPassword().equals(request.getPassword())) {
                String roleStr = user.getRole() != null ? user.getRole().name() : null;
                return new AuthResponse("dummy-token", user.getUsername(), roleStr, "Login successful");
            }
        }
        throw new RuntimeException("Invalid username or password");
    }

    public AuthResponse signup(com.satya.CRM.dto.SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        com.satya.CRM.enums.Role role;
        try {
            role = com.satya.CRM.enums.Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role");
        }

        if (role == com.satya.CRM.enums.Role.ADMIN) {
            throw new RuntimeException("Registration with ADMIN role is not allowed");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword()) // Plain text as requested
                .role(role)
                .build();

        userRepository.save(user);

        return new AuthResponse("dummy-token", user.getUsername(), role.name(), "User registered successfully");
    }
}
