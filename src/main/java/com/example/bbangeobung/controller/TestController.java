package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseDto test(@AuthenticationPrincipal OAuth2User principal) {

        System.out.println(principal);
        return ResponseDto.of(HttpStatus.OK,"test");
    }
}
