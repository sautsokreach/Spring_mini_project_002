package com.example.group02_spring_mini_project001.userModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private Integer userId;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role ="role_user";
}
