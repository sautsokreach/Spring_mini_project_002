package com.example.group02_spring_mini_project001.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super("Book Not Found");
    }
}
