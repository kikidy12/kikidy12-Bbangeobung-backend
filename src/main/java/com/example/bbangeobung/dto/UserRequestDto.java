package com.example.bbangeobung.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    private String username;

    public String password;
    public String currentPassword;
}

    // 이미지 바꾸기 구현 못함
