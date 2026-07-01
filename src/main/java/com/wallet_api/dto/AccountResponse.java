package com.wallet_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.wallet_api.entity.AccountType;
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
public class AccountResponse {

    private Long id;
    private String name;
    private BigDecimal balance;
    private AccountType accountType;
    private LocalDateTime createdAt;

}
