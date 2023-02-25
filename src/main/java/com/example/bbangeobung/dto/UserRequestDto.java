package com.example.bbangeobung.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    public String newPassword;
    public String currentPassword;
}

    // 이미지 바꾸기 구현 못함
