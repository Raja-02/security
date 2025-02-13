package com.example.spring_security_client.service;

import com.example.spring_security_client.entity.Token;
import com.example.spring_security_client.entity.User;
import com.example.spring_security_client.model.UserModel;
import com.example.spring_security_client.repository.RegistrationRepository;
import com.example.spring_security_client.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImplemention implements RegistartionService{

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public User RegisterUser(UserModel userModel) {
        User user=new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("User");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        registrationRepository.save(user);
        return user;
    }

    @Override
    public void SaveTokenAlongWithUser(User user, String token) {
        Token token1=new Token(user,token);
        tokenRepository.save(token1);
    }

    @Override
    public String ValidateToken(String token) {
        Token token1=tokenRepository.findByToken(token);
        if(token1==null)
            return "Invalid User";
        User user=token1.getUser();
        Calendar cal=Calendar.getInstance();
        if((token1.getExpireTime().getTime()-cal.getTime().getTime())<=0)
            return "Session Expired";
        user.setEnabled(true);
        registrationRepository.save(user);
        return "Valid";
    }
}
