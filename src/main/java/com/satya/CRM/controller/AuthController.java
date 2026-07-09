package com.satya.CRM.controller;

import com.satya.CRM.dto.AuthRequest;
import com.satya.CRM.dto.AuthResponse;
import com.satya.CRM.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public AuthResponse signup(@Valid @RequestBody com.satya.CRM.dto.SignupRequest request) {
        return authService.signup(request);
    }
}
