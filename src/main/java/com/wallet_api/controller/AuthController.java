package com.wallet_api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet_api.dto.AuthResponse;
import com.wallet_api.dto.LoginRequest;
import com.wallet_api.dto.RegisterRequest;
import com.wallet_api.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@Valid @RequestBody RegisterRequest userRegister) {
        AuthResponse user = authService.registerUser(userRegister);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@Valid @RequestBody LoginRequest userLogin) {
        AuthResponse user = authService.loginUser(userLogin);
        return ResponseEntity.ok(user);
    }

    
}
