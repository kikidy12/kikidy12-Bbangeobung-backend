package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.LoginRequestDto;
import com.example.bbangeobung.dto.SignupRequestDto;
import com.example.bbangeobung.dto.UserRequestDto;
import com.example.bbangeobung.dto.UserResponseDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Tag(name = "User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    // 회원가입 하기
    @PostMapping("/user/signup")
    @SecurityRequirements()
    @Operation(summary = "회원가입하기", description = "회원가입하기 이메일 중복 불가")
    public ResponseDto<UserResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return ResponseDto.of(HttpStatus.OK, "회원가입 성공", userService.signup(signupRequestDto));
    }

    // 로그인 하기
    @PostMapping("/user/login")
    @SecurityRequirements()
    @Operation(summary = "로그인 하기", description = "로그인 accessToken 발급")
    public ResponseDto<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseDto.of(HttpStatus.OK, "로그인 성공", userService.login(loginRequestDto, response));
    }


//    //  로그아웃 처리
//    @GetMapping("/user/logout")
//    public ResponseDto<UserResponseDto> lougout(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.invalidate();
//        return ResponseDto.of(HttpStatus.OK, "로그아웃 되었습니다.");
//
//    }

    // 마이페이지 이름 수정
    @PutMapping("/user")
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정/ 이름, 비밀번호 별도로 수정 가능 비밀번호를 변경하려면 newPassword, currentPassword는 같이 집어 넣어야함")
    public ResponseDto<UserResponseDto> update(
            @RequestBody UserRequestDto requestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseDto.of(HttpStatus.OK,"수정되었습니다.",userService.update(requestDto,userDetails.getUser()));

    }

    // 회원 삭제
    @DeleteMapping("/user")
    public ResponseDto<UserResponseDto> delete(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        userService.delete(userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK,"삭제되었습니다.");
    }





}
