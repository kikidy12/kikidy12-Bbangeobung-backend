package com.example.bbangeobung.config;

import com.example.bbangeobung.jwt.JwtAuthFilter;
import com.example.bbangeobung.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .mvcMatchers("/api-docs")
                .mvcMatchers("/docs/**")
                .mvcMatchers("/version")
                .mvcMatchers("/swagger-ui/**")
                .mvcMatchers("/h2-console/**")
                .mvcMatchers("/public");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()

                .authorizeRequests()
                .antMatchers("/api/user/signup", "/api/user/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/store/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/store/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/store/{storeId}").permitAll()
                .antMatchers("/api/**").authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

