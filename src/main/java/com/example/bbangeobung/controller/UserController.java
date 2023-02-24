package com.example.bbangeobung.controller;

import com.example.bbangeobung.dto.LoginRequestDto;
import com.example.bbangeobung.dto.SignupRequestDto;
import com.example.bbangeobung.dto.UserRequestDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private UserService userService;


    // 회원가입 하기
    @PostMapping("/user/signup")
    public String signup(SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "redirect:/api/user/login";
    }

    // 로그인 하기
    @ResponseBody
    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "redirect:/api/store";
    }


    // 마이페이지
    @GetMapping("/user")
    public ModelAndView myPage() {
        return new ModelAndView("user");
    }

//    @PutMapping("/user")
//    public String update(@PathVariable Long id, @RequestBody UserRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return userService.userUpdate(id,requestDto,userDetails.getUser() );
//    }






}
