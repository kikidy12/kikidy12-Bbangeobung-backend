package com.example.bbangeobung.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .title("빵어붕 API 문서") // 타이틀
                .version("1.0") // 문서 버전
                .description("post api docs") // 문서 설명
                .contact(new Contact()
                        .name("FE: 이한결")
                        .name("FE: 이현동")
                        .name("FE: 최하영")
                        .name("BE: 권성민")
                        .name("BE: 이선옥")
                        .name("BE: 김동민")
                        .name("BE: 손채이"));

        return new OpenAPI().info(info);
    }
}