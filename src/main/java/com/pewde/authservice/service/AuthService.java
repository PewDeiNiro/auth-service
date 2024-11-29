package com.pewde.authservice.service;

import com.pewde.authservice.entity.Token;
import com.pewde.authservice.entity.User;
import com.pewde.authservice.exception.InvalidPasswordException;
import com.pewde.authservice.exception.UserDoesNotExistsException;
import com.pewde.authservice.exception.UsernameAlreadyExistsException;
import com.pewde.authservice.mapper.UserMapper;
import com.pewde.authservice.repository.TokenRepository;
import com.pewde.authservice.repository.UserRepository;
import com.pewde.authservice.request.SignRequest;
import com.pewde.authservice.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserMapper userMapper;

    public AuthResponse signUp(SignRequest request){
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }
        User user = userMapper.mapSignRequestToUser(request);
        authorize(user);
        return new AuthResponse(user.getId(), user.getToken().getToken());
    }

    public AuthResponse signIn(SignRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserDoesNotExistsException::new);
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException();
        }
        authorize(user);
        return new AuthResponse(user.getId(), user.getToken().getToken());
    }

    public AuthResponse signOut(SignRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserDoesNotExistsException::new);
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException();
        }
        Token token = user.getToken();
        token.setExpiry(new Date());
        tokenRepository.saveAndFlush(token);
        return new AuthResponse(user.getId(), user.getToken().getToken());
    }

    private void authorize(User user){
        tokenRepository.findByUser(user).ifPresent(token -> {
            user.setToken(null);
            token.setUser(null);
            tokenRepository.delete(token);
        });
        Token token = Token.builder().token(UUID.randomUUID().toString().replace("-", ""))
                .release(new Date())
                .expiry(Date.from(LocalDateTime.now().plusHours(6).atZone(ZoneId.systemDefault()).toInstant()))
                .user(user).build();
        user.setToken(token);
        userRepository.saveAndFlush(user);
    }

    @Scheduled(fixedDelayString = "PT01H", initialDelayString = "PT01H")
    public void clearExpiryTokens(){
        List<Token> tokens = tokenRepository.findAll();
        Date now = new Date();
        for (Token token : tokens){
            if (token.getExpiry().before(now)){
                tokenRepository.delete(token);
            }
        }
        tokenRepository.flush();
    }

}
