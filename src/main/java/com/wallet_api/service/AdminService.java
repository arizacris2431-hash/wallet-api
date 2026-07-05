package com.wallet_api.service;


import com.wallet_api.dto.UpdateUserRequest;
import com.wallet_api.dto.UserResponse;
import com.wallet_api.entity.User;
import com.wallet_api.exception.BadRequestException;
import com.wallet_api.exception.UserNotFoundException;
import com.wallet_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers () {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToResponse).toList();
    }


    public UserResponse findById (Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        return mapToResponse(user);

    }

    public UserResponse updateUser (Long id, UpdateUserRequest userRequest) {
        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRol(userRequest.getRol());

        User saveDB = userRepository.save(user);

        return mapToResponse(saveDB);
    }

    public void deleteUser (Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        userRepository.delete(user);

    }

    private UserResponse mapToResponse (User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .rol(user.getRol())
                .build();
    }
}
