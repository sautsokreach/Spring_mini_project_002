package com.example.group02_spring_mini_project001.controller;

import com.example.group02_spring_mini_project001.exception.BlankException;
import com.example.group02_spring_mini_project001.exception.NotFoundIDException;
import com.example.group02_spring_mini_project001.model.TaskRequest;
import com.example.group02_spring_mini_project001.model.entity.Task;
import com.example.group02_spring_mini_project001.model.respone.ApiResponse;
import com.example.group02_spring_mini_project001.service.task.TaskService;
import com.example.group02_spring_mini_project001.userModel.AppUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
//@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // get all tasks
    @GetMapping("/")
    @Operation(summary = "Get all tasks")
    public ResponseEntity<?> getAllTask(){
        List<Task> taskList = taskService.getAllTasks();
        ApiResponse<List<Task>> taskApiResponse = new ApiResponse<>(
                taskList,
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(taskApiResponse);
    }

    // Insert new task
    @PostMapping("/tasks/users")
    @Operation(summary = "Add New Task")
    public ResponseEntity<?> addNewTask(@RequestBody TaskRequest taskRequest){
        if(taskRequest.getTaskName().isBlank() || taskRequest.getDescription().isBlank()||taskRequest.getDate().toString().isBlank()){
            throw new BlankException();
        }
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskRequest.setUserId(appUserDto.getUserId());
        Task newTask = taskService.addNewTask(taskRequest);
        ApiResponse response = new ApiResponse(
                newTask,
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(response);
    }
    // Get task by id
    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
        Task getTask =  taskService.getTaskById(id);
        if(getTask == null){
            throw new NotFoundIDException("Incorrect ID");
        }
        Task task =  taskService.getTaskById(id);
        ApiResponse<Task> taskApiResponse = new ApiResponse<>(
                task,
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(taskApiResponse);
    }

    // update_vuthin
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/users")
    @Operation(summary = "Update task by id for current user")
    public ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody TaskRequest taskRequest){
        Task getTask =  taskService.getTaskById(id);
        if(getTask == null){
            throw new NotFoundIDException("Incorrect ID");
        }
        if(taskRequest.getTaskName().isBlank() || taskRequest.getDescription().isBlank()||taskRequest.getDate().toString().isBlank()){
            throw new BlankException();
        }
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Task task =  taskService.updateTask( id,taskRequest, appUserDto.getUserId());
        return ResponseEntity.ok(new ApiResponse<>(
                task,
                LocalDateTime.now(),
                true
        ));
    }
    @GetMapping("{taskId}/users")
    @Operation(summary = "Get task by id for current user")
    public ResponseEntity<?> getTaskByIdForCurrentUser(@PathVariable Integer taskId){
        Task getTask =  taskService.getTaskById(taskId);
        if(getTask == null){
            throw new NotFoundIDException("Incorrect ID");
        }
        Task task = taskService.getTaskByIdForCurrentUser(taskId, getCurrentUser());
        ApiResponse response = new ApiResponse(
                task,
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(response);
    }
    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    @GetMapping("/users")
    @Operation(summary = "Get all task for current user")
    public ResponseEntity<?> getAllTaskByCurrentUser(){
        List<Task> task = taskService.getAllTaskByCurrentUser(getCurrentUser());
        ApiResponse<List<Task>> response = new ApiResponse<>(
                task,
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{taskId}/users")
    @Operation(summary = "Delete task by id for current user")
    public ResponseEntity<?> deleteTaskById(@PathVariable Integer taskId){
        Task getTask =  taskService.getTaskById(taskId);
        if(getTask == null){
            throw new NotFoundIDException("Incorrect ID");
        }
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskService.deleteTaskById(taskId,appUserDto.getUserId());
        ApiResponse response = new ApiResponse<>(
                "Delete this task id : " + taskId + " is successful !!",
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/tasks/status/users")
    @Operation(summary = "filter task by status for current user")
    public ResponseEntity<?> getTaskStatusUsers(@RequestParam String status,boolean Ascending, boolean Decending, Integer page , Integer size){
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> task = taskService.getTaskStatusUser(status,appUserDto.getUserId(),Ascending,Decending,(page-1)*size,size);
        ApiResponse response = new ApiResponse<>(
                task,
                LocalDateTime.now(),
                true
        );
        return ResponseEntity.ok(response);
    }


    // Filter task by status for current user

}
