package com.example.group02_spring_mini_project001.exception;

public class BlankException extends RuntimeException{
    public BlankException(){
        super("Blank Space is Not Allow");
    }
}
