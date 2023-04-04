package com.example.group02_spring_mini_project001.repository;

import com.example.group02_spring_mini_project001.userModel.AppUser;
import com.example.group02_spring_mini_project001.userModel.AppUserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppUserRepository {

    @Select("""
            SELECT * FROM user_tb
            WHERE email = #{email}
            """)
    @Result(property = "userId",column = "id")
    AppUser findByEmail(String email);
    @Select("""
            INSERT INTO user_tb ( email, password, role)
            VALUES ( #{user.email}, #{user.password}, #{user.role})
            RETURNING *
            """)
    @Result(property = "userId",column = "id")
    AppUser insertUser(@Param("user") AppUserRequest appUserRequest);
}
