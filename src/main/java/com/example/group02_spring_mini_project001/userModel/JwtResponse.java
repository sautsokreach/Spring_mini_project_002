package com.example.group02_spring_mini_project001.userModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Integer userId;
    private String email;
    private String token;
}
