package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.jwt.JwtUtil;
import com.example.bbangeobung.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UserService userService;

    @GetMapping("/login/oauth/success")
    public ResponseDto oauthLoginSuccess(@RequestParam String accessToken, HttpServletResponse response) {

        return ResponseDto.of(HttpStatus.OK, "소셜 로그인 성공", userService.getUserByToken(accessToken, response));
    }
}
