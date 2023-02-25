package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.LoginRequestDto;
import com.example.bbangeobung.dto.SignupRequestDto;
import com.example.bbangeobung.dto.UserRequestDto;
import com.example.bbangeobung.dto.UserResponseDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    // 회원가입 하기
    @PostMapping("/user/signup")
    @SecurityRequirements()
    public ResponseDto<UserResponseDto> signup(SignupRequestDto signupRequestDto) {
        return ResponseDto.of(HttpStatus.OK, "회원가입 성공", userService.signup(signupRequestDto));
    }

    // 로그인 하기
    @PostMapping("/user/login")
    @SecurityRequirements()
    public ResponseDto<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseDto.of(HttpStatus.OK, "로그인 성공", userService.login(loginRequestDto, response));
    }


//     // 로그아웃 처리
//    @GetMapping("/user")
//    public ModelAndView myPage() {
//        return new ModelAndView("user");
//    }

    // 마이페이지 이름 수정
    @PutMapping("/user")
    public void update(@RequestBody UserRequestDto requestDto, HttpServletResponse response){
//        return ResponseDto.of(HttpStatus.OK, "수정되었습니다.", userService.update(requestDto, response));
        userService.update(requestDto,response);

    }

//    // 회원 삭제
//    public String delete(@PathVariable Long id){
//        userService.UserDelete(id);
//        return "redirect:/api/user/login";
//    }





}
