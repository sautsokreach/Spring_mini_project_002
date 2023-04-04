package com.example.group02_spring_mini_project001.service.category;

import com.example.group02_spring_mini_project001.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryByUser(Integer user);

    List<Category> getAllCategories();

    Category getCategoryByID(Integer id);

    Category updateCategoryByID(Integer id, Category categoryRequest, String email);

    Category deleteCategoryByID(Integer id,Integer userId);
    Category addCategoryByUser(Category category);

    Category getCategoryByIdByUser(Integer userId, Integer id);

    List<Category> getCategoryByUser(Integer userId, boolean ascending, boolean decending, Integer page, Integer size);
}
