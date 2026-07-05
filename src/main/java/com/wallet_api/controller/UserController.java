package com.wallet_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet_api.dto.UserProfileResponse;
import com.wallet_api.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> UsersMe () {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok (UserProfileResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .rol(user.getRol())
            .registrationDate(user.getRegistrationDate())
            .build());
    }


}
