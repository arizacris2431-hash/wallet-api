package com.wallet_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.wallet_api.entity.TransactionType;

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
public class TransactionResponse {

    private Long id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDateTime createdAt;
    private String categoryName;
    private String accountName;

}
