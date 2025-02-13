package com.example.spring_security_client.events;


import com.example.spring_security_client.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class Event extends ApplicationEvent {
    private User user;
    private String url;

    public Event(User user, String url) {
        super(user);
        this.user=user;
        this.url=url;
    }
}
