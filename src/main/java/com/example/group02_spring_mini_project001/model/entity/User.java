package com.example.group02_spring_mini_project001.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private String role;
}
