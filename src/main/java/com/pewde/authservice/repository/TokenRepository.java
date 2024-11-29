package com.pewde.authservice.repository;

import com.pewde.authservice.entity.Token;
import com.pewde.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByUser(User user);

}
