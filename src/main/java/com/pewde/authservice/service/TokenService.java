package com.pewde.authservice.service;

import com.pewde.authservice.entity.User;
import com.pewde.authservice.exception.InvalidPasswordException;
import com.pewde.authservice.exception.UserDoesNotExistsException;
import com.pewde.authservice.repository.UserRepository;
import com.pewde.authservice.request.SignRequest;
import com.pewde.authservice.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse expendToken(SignRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserDoesNotExistsException::new);
        if (!user.getPassword().equals(request.getPassword())){
            throw new InvalidPasswordException();
        }
        user.getToken().setExpiry(Date.from(LocalDateTime.now().plusHours(6).atZone(ZoneId.systemDefault()).toInstant()));
        userRepository.saveAndFlush(user);
        return new AuthResponse(user.getId(), user.getToken().getToken());
    }

}
