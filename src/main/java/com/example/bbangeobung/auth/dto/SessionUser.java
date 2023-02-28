package com.example.bbangeobung.auth.dto;

import com.example.bbangeobung.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String username;
    private String email;

    public SessionUser(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
