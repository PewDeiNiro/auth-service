package com.pewde.authservice.controller;

import com.pewde.authservice.request.SignRequest;
import com.pewde.authservice.response.AuthResponse;
import com.pewde.authservice.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TokenController", description = "Операции взаимодействия с токеном(продление)")
@Validated
@RequestMapping("/api/auth/token")
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Продление токена")
    @PostMapping("/expend")
    public AuthResponse expendToken(@RequestBody @Valid SignRequest request){
        return tokenService.expendToken(request);
    }

}
