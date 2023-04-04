package com.example.group02_spring_mini_project001.service.task;

import com.example.group02_spring_mini_project001.model.TaskRequest;
import com.example.group02_spring_mini_project001.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task addNewTask(TaskRequest taskRequest);
    Task getTaskById(Integer id);

    Task updateTask(Integer id, TaskRequest taskRequest,Integer Id);

    List<Task> getAllTaskByCurrentUser(String email);

    Task getTaskByIdForCurrentUser(Integer taskId, String email);

    void deleteTaskById(Integer taskId, Integer userId);

    List<Task> getTaskStatusUser(String status, Integer userId, boolean ascending, boolean decending, Integer page, Integer size);
}
