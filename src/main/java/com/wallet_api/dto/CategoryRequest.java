package com.wallet_api.dto;

import com.wallet_api.entity.CategoryType;

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
public class CategoryRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The type is required")
    private CategoryType type;
}
