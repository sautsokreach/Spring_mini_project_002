package com.example.group02_spring_mini_project001.exception;

public class InvalidStatusException extends RuntimeException{
    public InvalidStatusException(String message) {
        super(message);
    }
}
