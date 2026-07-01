package com.wallet_api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wallet_api.dto.AuthResponse;
import com.wallet_api.dto.LoginRequest;
import com.wallet_api.dto.RegisterRequest;
import com.wallet_api.entity.Role;
import com.wallet_api.entity.User;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse registerUser (RegisterRequest requestUser) {
        boolean existsByEmail = userService.existsByEmail(requestUser.getEmail());

        if (existsByEmail) {
            throw new IllegalArgumentException("This email address is already registered");
        }

        User user = new User();

        user.setEmail(requestUser.getEmail());
        user.setName(requestUser.getName().trim());
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));

        user.setRol(Role.CLIENTE);

        User saveDB = userService.save(user);

        String token = jwtService.generateToken(saveDB.getEmail());

        return AuthResponse.builder()
            .token(token)
            .email(saveDB.getEmail())
            .name(saveDB.getName())
            .rol(saveDB.getRol())
            .build();
    }

    public AuthResponse loginUser (LoginRequest loginUser) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
            .token(token)
            .email(user.getEmail())
            .name(user.getName())
            .rol(user.getRol())
            .build();
    }

}
