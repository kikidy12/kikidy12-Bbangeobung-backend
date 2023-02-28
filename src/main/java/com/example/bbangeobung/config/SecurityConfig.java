package com.example.bbangeobung.config;

//import com.example.bbangeobung.auth.CustomOAuth2UserService;
import com.example.bbangeobung.auth.CustomOAuth2UserService;
import com.example.bbangeobung.cors.CorsFilter;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.jwt.JwtAuthFilter;
import com.example.bbangeobung.jwt.JwtUtil;
import com.nimbusds.oauth2.sdk.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
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
                .antMatchers(HttpMethod.GET, "/api/store/{storeId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/store/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/store/{storeId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/fishBreadType/{fishBreadTypeId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/fishBreadType/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/review/{reviewId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/review/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/comment/{commentId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/comment/").permitAll()
                .antMatchers("/api/**").authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(new CorsFilter(), UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JwtAuthFilter(jwtUtil), CorsFilter.class);
                http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
//                        .and().successHandler();

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

