package com.pewde.authservice.controller;

import com.pewde.authservice.request.SignRequest;
import com.pewde.authservice.response.AuthResponse;
import com.pewde.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AuthController", description = "Операции авторизации/регистрации")
@Validated
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Регистрация пользователя в системе")
    @PostMapping("/sign-up")
    public AuthResponse signUp(@RequestBody @Valid SignRequest request) {
        return authService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя в системе")
    @PostMapping("/sign-in")
    public AuthResponse signIn(@RequestBody @Valid SignRequest request) {
        return authService.signIn(request);
    }

    @Operation(summary = "Выход из аккаунта")
    @PostMapping("/sign-out")
    public AuthResponse signOut(@RequestBody @Valid SignRequest request) {
        return authService.signOut(request);
    }

}
