package com.example.group02_spring_mini_project001.repository;

import com.example.group02_spring_mini_project001.model.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {

    @Select("""
               select *
               from category_tb where user_id= #{id};     
            """
    )
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    List<Category> getCategoryByUser(Integer user);

    @Select("""
            select*
            from category_tb;
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    List<Category> getAllCategories();


    @Select("""
            select*
            from category_tb
            where id =#{id}
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    Category getCategoryByID(Integer id);


    @Select("""
            update category_tb
            set name= #{Category.categoryName}
            where id=#{id} And user_id = #{user_id} returning *
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    Category updateCategoryByID(@Param("id") Integer id, @Param("Category") Category categoryRequest, Integer user_id);

    @Select("""
            SELECT id FROM user_tb
            WHERE email = #{email}
            """)
            @Result (property = "categoryId",column = "id")
//    @ResultMap("cateMap")
    Category getCurrentUserId(String email);


    @Select("""
            delete from category_tb
            where id=#{id} and user_id = #{userId} returning *         
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    Category deleteCategoryByID(Integer id, Integer userId);

    @Select("""
            insert into category_tb values(default,#{categoryName},#{date},#{userId}) returning *
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    Category addCategoryByUser(Category category);


    @Select("""
            select * from category_tb where id = #{id} AND user_id =#{userId};
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    Category getCategoryByIdByUser(Integer userId, Integer id);
    @Select("""
            select * from category_tb where user_id =#{userId} Order by id Desc OFFSET #{page} LIMIT  #{size};
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    List<Category> getAllCategoryByUserDesc(Integer userId, Integer page, Integer size);
    @Select("""
            select * from category_tb where user_id =#{userId} Order by id Asc OFFSET #{page} LIMIT  #{size};
            """)
    @Result(property="categoryId", column = "id")
    @Result(property="categoryName", column = "name")
    @Result(property="userId", column = "user_id")
    List<Category> getAllCategoryByUserAsc(Integer userId, Integer page, Integer size);
}
