package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;

    private String email;

    private String username;

    private UserRoleEnum role;

    @Builder
    public UserResponseDto(Long id, String email, String username, UserRoleEnum role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
    }
}
