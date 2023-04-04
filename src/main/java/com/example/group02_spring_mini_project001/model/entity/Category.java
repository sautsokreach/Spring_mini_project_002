package com.example.group02_spring_mini_project001.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer categoryId;
    private String categoryName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime date;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer userId;
}
