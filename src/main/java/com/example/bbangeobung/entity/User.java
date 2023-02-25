package com.example.bbangeobung.entity;

import com.example.bbangeobung.dto.UserRequestDto;
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

    //닉네임 추가
    @Column(nullable = false)
    private String nikName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany
    private Set<Store> stores;


    public User(String nikName, String email, String username, String password, UserRoleEnum role) {
        this.nikName = nikName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void update(UserRequestDto requestDto){
        this.nikName = requestDto.getNikName();
        this.email = requestDto.getEmail();
    }



}
