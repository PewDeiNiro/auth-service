package com.pewde.authservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignRequest {

    @Schema(description = "Имя пользователя")
    @NotEmpty
    private String username;

    @Schema(description = "Пароль")
    @NotEmpty
    private String password;

}
