package com.wallet_api.dto;


import java.time.LocalDateTime;

import com.wallet_api.entity.Role;

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
public class UserProfileResponse {

    private Long id;
    private String email;
    private String name;
    private Role rol;
    private LocalDateTime registrationDate;

}
