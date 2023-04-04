package com.example.group02_spring_mini_project001.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(){
        super("Author Not Found");
    }
}
