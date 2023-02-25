package com.example.bbangeobung.service;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.LoginRequestDto;
import com.example.bbangeobung.dto.SignupRequestDto;
import com.example.bbangeobung.dto.UserRequestDto;
import com.example.bbangeobung.dto.UserResponseDto;
import com.example.bbangeobung.entity.User;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.jwt.JwtUtil;
import com.example.bbangeobung.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@Setter
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin_token}")
    private String ADMIN_TOKEN;

    public UserResponseDto signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        String email = signupRequestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = User.builder().email(email).username(username).password(password).role(role).build();
        user = userRepository.save(user);

        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));

        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public String userUpdate(UserRequestDto requestDto,HttpServletResponse response) {

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

    }

    @Transactional
    public void UserDelete(Long id) {
        userRepository.deleteById(id);
    }
}
