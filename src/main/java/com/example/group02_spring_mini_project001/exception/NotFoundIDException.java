package com.example.group02_spring_mini_project001.exception;

public class NotFoundIDException extends RuntimeException{
    public NotFoundIDException(String message) {
        super(message);
    }
}
