package com.example.group02_spring_mini_project001.service.category;


import com.example.group02_spring_mini_project001.model.entity.Category;
import com.example.group02_spring_mini_project001.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class CategoryServiceImplement implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplement(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategoryByUser(Integer user) {
        return categoryRepository.getCategoryByUser(user);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories() ;
    }

    @Override
    public Category getCategoryByID(Integer id) {
        return categoryRepository.getCategoryByID(id);
    }

    @Override
    public Category updateCategoryByID(Integer id, Category categoryRequest, String email) {

        Category user_id = categoryRepository.getCurrentUserId(email);

        return categoryRepository.updateCategoryByID(id, categoryRequest, user_id.getCategoryId());
    }

    @Override
    public Category deleteCategoryByID(Integer id, Integer userId) {
        return categoryRepository.deleteCategoryByID(id,userId);
    }

    @Override
    public Category addCategoryByUser(Category category) {
        return categoryRepository.addCategoryByUser(category);
    }

    @Override
    public Category getCategoryByIdByUser(Integer userId, Integer id) {
        return categoryRepository.getCategoryByIdByUser(userId,id);
    }

    @Override
    public List<Category> getCategoryByUser(Integer userId, boolean ascending, boolean decending, Integer page, Integer size) {
        if(decending){
            return categoryRepository.getAllCategoryByUserDesc(userId,page,size);
        }else
            return categoryRepository.getAllCategoryByUserAsc(userId,page,size);

    }
}
