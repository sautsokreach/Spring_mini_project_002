package com.example.group02_spring_mini_project001.exception;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(){
        super("Password is not correct");
    }
}
