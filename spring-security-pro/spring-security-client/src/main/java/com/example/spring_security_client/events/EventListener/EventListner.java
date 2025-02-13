package com.example.spring_security_client.events.EventListener;

import com.example.spring_security_client.entity.Token;
import com.example.spring_security_client.entity.User;
import com.example.spring_security_client.events.Event;
import com.example.spring_security_client.repository.TokenRepository;
import com.example.spring_security_client.service.RegistartionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class EventListner implements ApplicationListener<Event> {

    @Autowired
    private RegistartionService registartionService;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void onApplicationEvent(Event event) {


        User user = event.getUser();

        // Token creation and saving it along with user
        String tokenString = UUID.randomUUID().toString();
        Token token = new Token(user, tokenString);
        tokenRepository.save(token);

        // Sending verification mail (log here for demonstration)
        String url = event.getUrl() + "/verifyuser?token=" + tokenString;
        log.info("Click here to verify: {}", url);
    }
}
