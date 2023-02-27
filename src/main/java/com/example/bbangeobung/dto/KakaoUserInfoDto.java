package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String email;
    private String nikename;

    public KakaoUserInfoDto(Long id, String email, String nikname) {
        this.id = id;
        this.email = email;
        this.nikename = nikname;
    }

}
