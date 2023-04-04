package com.example.group02_spring_mini_project001.model.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    private LocalDateTime date;
    private Boolean status;
}
