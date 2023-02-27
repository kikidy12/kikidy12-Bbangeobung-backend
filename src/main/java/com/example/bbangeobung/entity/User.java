package com.example.bbangeobung.entity;

import com.example.bbangeobung.dto.UserRequestDto;
import com.example.bbangeobung.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;
    private Long kakaoId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany
    private Set<Store> stores;

    public User(String email,Long kakaoId, String username, String password, UserRoleEnum role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.kakaoId = kakaoId;
    }
    @Builder
    public User(String email, String username, String password, UserRoleEnum role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    //    public void UserUpdate(UserRequestDto requestDto) {
//        this.username = requestDto.getUsername();
//        this.password = requestDto.getPassword();
//        this.role = role;
//    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}
