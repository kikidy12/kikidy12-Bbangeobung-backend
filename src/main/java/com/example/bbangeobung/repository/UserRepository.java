package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
