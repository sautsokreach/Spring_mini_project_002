package com.example.group02_spring_mini_project001;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SecurityScheme(
        name = "bearerAuth",  // can be set to anything
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@SpringBootApplication
public class Group02SpringMiniProject001Application {

    public static void main(String[] args) {
        SpringApplication.run(Group02SpringMiniProject001Application.class, args);
    }

}
