package com.example.group02_spring_mini_project001.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super("Category Not Found");
    }
}
