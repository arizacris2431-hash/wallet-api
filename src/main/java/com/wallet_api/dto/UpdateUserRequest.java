package com.wallet_api.dto;

import com.wallet_api.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @Email(message = "Enter a valid email address")
    @NotBlank(message = "The name is required")
    private String email;

    @NotNull(message = "The type is required")
    private Role rol;
}
