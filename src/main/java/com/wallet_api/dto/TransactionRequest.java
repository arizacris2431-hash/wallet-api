package com.wallet_api.dto;


import java.math.BigDecimal;

import com.wallet_api.entity.TransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotBlank(message = "The description is required")
    private String description;

    @NotNull(message = "The amount is required")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    private BigDecimal amount;

    @NotNull(message = "The transaction is required")
    private TransactionType type;

    @NotNull(message = "The Id category is required")
    private Long categoryId;

    @NotNull(message = "The Id account is required")
    private Long accountId;
    
}
