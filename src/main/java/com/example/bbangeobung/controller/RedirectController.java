package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {


    @GetMapping("/login/oauth/success")
    public ResponseDto oauthLoginSuccess(@RequestParam String accessToken, HttpServletResponse response) {

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

        return ResponseDto.of(HttpStatus.OK, "소셜 로그인 성공");
    }
}
