package com.example.group02_spring_mini_project001.controller;


import com.example.group02_spring_mini_project001.exception.BlankException;
import com.example.group02_spring_mini_project001.exception.NotFoundIDException;
import com.example.group02_spring_mini_project001.model.entity.Category;
import com.example.group02_spring_mini_project001.model.entity.User;
//import com.example.group02_spring_mini_project001.model.respone.ApiRespone;
import com.example.group02_spring_mini_project001.service.category.CategoryService;
import com.example.group02_spring_mini_project001.userModel.ApiResponse;
import com.example.group02_spring_mini_project001.userModel.AppUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.internal.bytebuddy.build.Plugin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    post Category By User
    @PostMapping("/categories/users")
    @Operation(summary = "Add new category")
    public ResponseEntity<?> addCategoryByUser(@RequestBody Category category){
        if(category.getCategoryName().isBlank()){
            throw new BlankException();
        }
        category.setDate(LocalDateTime.now());
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        category.setUserId(appUserDto.getUserId());
        ApiResponse<Category> respone = ApiResponse.<Category>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(categoryService.addCategoryByUser(category))
                .build();
        return ResponseEntity.ok(respone);
    }
//    get all categories
    @GetMapping("/categories")
    @Operation(summary = "Get all categories")
    public ResponseEntity<?> getAllCategories(){
        List<Category> category = categoryService.getAllCategories();
        ApiResponse<List<Category>> respone = ApiResponse.<List<Category>>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(category)
                .build();
        return ResponseEntity.ok(respone);
    }
//    get categories by id
    @GetMapping("/categories/{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<?> getCategoryByID(@PathVariable Integer id) {
        Category category = categoryService.getCategoryByID(id);
        if (category == null){
            throw new NotFoundIDException("category Not Found");
        }
        ApiResponse<Category> respone = ApiResponse.<Category>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(category)
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
//    update user by id
    @PutMapping("/categories/{id}/users")
    @Operation(summary = "Update category by id")
    public ResponseEntity<?> updateCategoryByID(@PathVariable Integer id, @RequestBody Category categoryRequest) {
        Category getCategory = categoryService.getCategoryByID(id);
        if(getCategory == null){
            throw new NotFoundIDException("category Id not Found");
        }
        if(categoryRequest.getCategoryName().isBlank()){
            throw new BlankException();
        }

        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse<Category> respone = ApiResponse.<Category>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(categoryService.updateCategoryByID(id, categoryRequest, getCurrentUser()))
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    //    delete category
    @DeleteMapping ("/categories/{id}/users")
    @Operation(summary = "Delete category by id for current user")
    public ResponseEntity<?> deleteCategoryByID(@PathVariable Integer id) {
        Category getCategory = categoryService.getCategoryByID(id);
        if(getCategory == null){
            throw new NotFoundIDException("category Id not Found");
        }
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categoryService.deleteCategoryByID(id,appUserDto.getUserId());
        ApiResponse<String> respone = ApiResponse.<String>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload("Delete this category id : "+id+" is successful !!")
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/users")
    @Operation(summary = "Get Category by id for current user")
    ResponseEntity<?> getAllCategoryByIdByUser(@PathVariable Integer id){
        Category getCategory = categoryService.getCategoryByID(id);
        if(getCategory == null){
            throw new NotFoundIDException("category Id not Found");
        }
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryService.getCategoryByIdByUser(appUserDto.getUserId(),id);
        if(category==null){
            throw new NotFoundIDException("Id not Found");
        }
        ApiResponse<Category> response = ApiResponse.<Category>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(category)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/categories/users")
    @Operation(summary = "Get all category for current user")
    ResponseEntity<?> getAllCategoryByUser(boolean Ascending, boolean Decending, Integer page , Integer size){
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse<List<Category>> response = ApiResponse.<List<Category>>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(categoryService.getCategoryByUser(appUserDto.getUserId(),Ascending,Decending,(page-1)*size,size))
                .build();
        return ResponseEntity.ok(response);
    }

}
