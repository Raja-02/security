package com.example.spring_security_client.repository;

import com.example.spring_security_client.entity.Token;
import com.example.spring_security_client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findByToken(String token);
}
