package com.pewde.authservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Authentication service",
                description = "Сервис для авторизации/регистрации",
                version = "1.0.0"
        )
)
public class SwaggerConfig {
}
