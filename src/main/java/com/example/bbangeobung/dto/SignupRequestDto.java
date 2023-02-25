package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nikName;

    private String email;
    private boolean admin = false;
    private String adminToken = "";
}