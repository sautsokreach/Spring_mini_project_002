package com.example.group02_spring_mini_project001.exception;

public class EmailException extends RuntimeException{
    public EmailException(){
        super("Email is not correct");
    }
}
