package com.example.group02_spring_mini_project001.service.task;


import com.example.group02_spring_mini_project001.exception.InvalidStatusException;
import com.example.group02_spring_mini_project001.exception.NotFoundIDException;
import com.example.group02_spring_mini_project001.model.TaskRequest;
import com.example.group02_spring_mini_project001.model.entity.Task;
import com.example.group02_spring_mini_project001.repository.TaskRepository;
import com.example.group02_spring_mini_project001.userModel.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class TaskServiceImplement implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImplement(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @Override
    public Task getTaskById(Integer id) {
        System.out.println(taskRepository.getTaskById(id));
        return taskRepository.getTaskById(id);
    }

    @Override
    public Task updateTask(Integer id, TaskRequest taskRequest,Integer userId) {
        if (taskRequest.getStatus().equalsIgnoreCase("is_cancelled") ||
                taskRequest.getStatus().equalsIgnoreCase("is_completed") ||
                taskRequest.getStatus().equalsIgnoreCase("is_in_progress") ||
                taskRequest.getStatus().equalsIgnoreCase("is_in_review")
        ){
        }else {
            throw new InvalidStatusException("This status is not correct : " + taskRequest.getStatus()+ " , Please input one of (is_cancelled, is_completed, is_in_progress, is_in_review)");
        }
        return taskRepository.updateTask(id,taskRequest,userId);
    }

    @Override
    public List<Task> getAllTaskByCurrentUser(String email) {
        Task userId = taskRepository.getCurrentUserId(email);
        List<Task> task = taskRepository.getAllTaskByCurrentUser(userId.getTaskId());
        if (task == null){
            try {
                throw new NotFoundIDException("Task doesn't have");
            } catch (NotFoundIDException e) {
                throw new RuntimeException(e);
            }
        }
        return task;
    }

    @Override
    public Task getTaskByIdForCurrentUser(Integer taskId, String email) {
        Task userId = taskRepository.getCurrentUserId(email);
        Task task = taskRepository.getTaskByIdForCurrentUser(taskId, userId.getTaskId());
        if (task == null){
            try {
                throw new NotFoundIDException("Id not found");
            } catch (NotFoundIDException e) {

            }
        }
        return task;
    }
    @Override
    public void deleteTaskById(Integer taskId, Integer userId) {
        if(taskRepository.deleteTaskById(taskId,userId)==null){
            throw new NotFoundIDException("Id Not Found");
        }
    }

    @Override
    public List<Task> getTaskStatusUser(String status, Integer userId, boolean ascending, boolean decending, Integer page, Integer size) {
        if (status.equalsIgnoreCase("is_cancelled") ||
                status.equalsIgnoreCase("is_completed") ||
                status.equalsIgnoreCase("is_in_progress") ||
                status.equalsIgnoreCase("is_in_review")
        ){
        }else {
            throw new InvalidStatusException("This status is not correct : " + status+ " , Please input one of (is_cancelled, is_completed, is_in_progress, is_in_review)");
        }
        if(ascending){
            return taskRepository.getTaskStatusUserAsc(status,userId,page,size);
        }else{
            return taskRepository.getTaskStatusUserDesc(status,userId,page,size);
        }
    }

    @Override
    public Task addNewTask(TaskRequest taskRequest) {
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId =  appUserDto.getUserId();
        if (taskRequest.getStatus().equalsIgnoreCase("is_cancelled") ||
                taskRequest.getStatus().equalsIgnoreCase("is_completed") ||
                taskRequest.getStatus().equalsIgnoreCase("is_in_progress") ||
                taskRequest.getStatus().equalsIgnoreCase("is_in_review")
        ){
            Task task = taskRepository.addNewTask(taskRequest,userId);
            return task;
        }else {
            throw new InvalidStatusException("This status is not correct : " + taskRequest.getStatus() + " , Please input one of (is_cancelled, is_completed, is_in_progress, is_in_review)");
        }
    }

}
