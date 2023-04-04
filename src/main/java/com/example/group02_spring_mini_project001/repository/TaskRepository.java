package com.example.group02_spring_mini_project001.repository;

import com.example.group02_spring_mini_project001.model.TaskRequest;
import com.example.group02_spring_mini_project001.model.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskRepository {

    // get all
    @Select("""
            select * from task_tb
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    List<Task> getAllTasks();


    // get Task by id


    // Insert new task

    @Select("""
            insert into task_tb(name, description, status, category_id, date, user_id)
            Values(#{tk.taskName}, #{tk.description}, #{tk.status}, #{tk.categoryId}, #{tk.date},#{userId})
            returning *
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Task addNewTask(@Param("tk") TaskRequest taskRequest, Integer userId);
    @Select("""
            Select * from task_tb
            where id = #{id};
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Task getTaskById(Integer id);

    // Update task
    @Select("""
            update task_tb
            set  name=#{task.taskName}, 
                description=#{task.description}, 
                date=#{task.date},
                status=#{task.status},
                user_Id=#{task.userId},
                category_id=#{task.categoryId}
            where id = #{id}  and user_id = #{userId}
            returning *  
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Task updateTask( Integer id,@Param("task") TaskRequest taskRequest,Integer userId);

    @Select("""
           select * from task_tb where user_id = #{userId}
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    List<Task> getAllTaskByCurrentUser(Integer userId);


    // get task by id for current user

    @Select("""
           select * from task_tb where user_id = #{userId} and  id = #{taskId}
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Task getTaskByIdForCurrentUser(Integer taskId, Integer userId);
    // delete task by id
    @Select("""
            delete from task_tb
            where id = #{taskId} and user_id = #{userId} returning *
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Task deleteTaskById(Integer taskId, Integer userId);

    @Select("""
            SELECT id From user_tb where email = #{email}
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Task getCurrentUserId(String email);
    @Select("""
           select * from task_tb where status = #{status} and  user_id = #{userId}
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    List<Task> getTaskStatusUser(String status, Integer userId);
    @Select("""
           select * from task_tb  where status = #{status} and  user_id = #{userId}  order by id asc OFFSET #{page} LIMIT  #{size}; 
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    List<Task> getTaskStatusUserAsc(String status, Integer userId, Integer page, Integer size);
    @Select("""
           select * from task_tb where status = #{status} and  user_id = #{userId} order by id desc   OFFSET #{page} LIMIT  #{size};
            """)
    @Result(property = "taskId",column = "id")
    @Result(property = "taskName", column = "name")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    List<Task> getTaskStatusUserDesc(String status, Integer userId, Integer page, Integer size);
}
