package com.wallet_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Esta clase contiene configuraciones y Beans que quiero registrar en el contenedor de Spring.
@Configuration
public class OpenApiConfig {

    // Si quiero que Spring administre un objeto, debo devolverlo desde un método anotado con Bean
    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("Wallet API")
                        .version("1.0.0")
                        .description("REST API for personal finance management.")
                )
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication")
                );

    }
}
