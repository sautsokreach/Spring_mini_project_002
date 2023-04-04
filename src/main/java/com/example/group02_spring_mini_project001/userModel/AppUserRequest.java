package com.example.group02_spring_mini_project001.userModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private String email;
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role ="role_user";
}
