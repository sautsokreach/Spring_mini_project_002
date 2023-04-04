package com.example.group02_spring_mini_project001.exception;

public class GenderException extends RuntimeException{
    public GenderException(){
        super("Gender is Not Correct...");
    }
}
