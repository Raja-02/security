package com.example.spring_security_client.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
//entity class
public class Token {
    private static final int time=5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date ExpireTime;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "User_id",
    nullable = false,
    foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
    private User user;

    public Token(User user,String token)
    {
        this.user=user;
        this.token=token;
        this.ExpireTime=calculateExpireTime(time);
    }
    public Token(String token)
    {
        super();
        this.token=token;
        this.ExpireTime=calculateExpireTime(time);
    }

    private Date calculateExpireTime(int time) {
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE,time);
        return new Date(cal.getTime().getTime());
    }
}
