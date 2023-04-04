package com.example.group02_spring_mini_project001.model;


import com.example.group02_spring_mini_project001.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private String categoryName;
    private Timestamp date;
    private User userId;
}
