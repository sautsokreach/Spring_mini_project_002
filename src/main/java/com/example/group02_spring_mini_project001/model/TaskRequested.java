package com.example.group02_spring_mini_project001.model;


import com.example.group02_spring_mini_project001.model.entity.Category;
import com.example.group02_spring_mini_project001.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequested {
    private Integer taskId;
    private String taskName;
    private String description;
    private Timestamp date;
    private User userId;
    private String status;
    private Category categoryId;
}
