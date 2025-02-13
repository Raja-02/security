package com.example.spring_security_client.service;

import com.example.spring_security_client.entity.User;
import com.example.spring_security_client.model.UserModel;

public interface RegistartionService {
    User RegisterUser(UserModel user);

    void SaveTokenAlongWithUser(User user, String token);

    String ValidateToken(String token);
}
