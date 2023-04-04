package com.example.group02_spring_mini_project001.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TaskRequest {
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String  taskName;
    private String  description;
    private LocalDateTime date;
    private String status;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private Integer userId;
    private Integer categoryId;
}
