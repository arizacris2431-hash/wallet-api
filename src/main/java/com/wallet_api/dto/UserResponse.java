package com.wallet_api.dto;

import com.wallet_api.entity.Role;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// Representa lo que la API quiere mostrar.
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Role rol;

}
