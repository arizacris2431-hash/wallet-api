package com.wallet_api.dto;

import com.wallet_api.entity.AccountType;

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
public class AccountRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The type is required")
    private AccountType accountType;
}
