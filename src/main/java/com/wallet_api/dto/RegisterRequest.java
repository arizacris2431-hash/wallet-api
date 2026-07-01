package com.wallet_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The email address is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "The password is required")
    @Size(min = 8, max = 20, message = "The password must be between 8 and 20 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.*_\\-]).*$", message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.")
    private String password;

}
