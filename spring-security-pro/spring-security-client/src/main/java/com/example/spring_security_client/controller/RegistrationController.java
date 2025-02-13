package com.example.spring_security_client.controller;

import com.example.spring_security_client.entity.User;
import com.example.spring_security_client.events.Event;
import com.example.spring_security_client.model.UserModel;
import com.example.spring_security_client.service.RegistartionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private RegistartionService registartionService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String RegisterUser(@RequestBody UserModel userModel, HttpServletRequest request)
    {
        User user=registartionService.RegisterUser(userModel);
        publisher.publishEvent(new Event(user,crateurl(request))
        );
        return "SuccessFully registerd";
    }

    @GetMapping("/verifyuser")
    public String verifyUser(@RequestParam("token") String token)
    {
        String result=registartionService.ValidateToken(token);
        if(result.equalsIgnoreCase("Valid"))
            return "Verifired Successfully";
        return "Bad User";
    }
    public String crateurl(HttpServletRequest request)
    {
        return "Http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
