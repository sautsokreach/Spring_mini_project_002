package com.example.group02_spring_mini_project001.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer taskId;
    private String taskName;
    private String description;
    private LocalDateTime date;
    private Integer userId;
    private String status;
    private Integer categoryId;
}
