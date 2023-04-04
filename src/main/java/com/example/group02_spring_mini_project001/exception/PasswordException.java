package com.example.group02_spring_mini_project001.exception;

public class PasswordException extends RuntimeException{
    public PasswordException(){

//        ^                 # start-of-string
//                (?=.*[0-9])       # a digit must occur at least once
//                (?=.*[a-z])       # a lower case letter must occur at least once
//        (?=.*[A-Z])       # an upper case letter must occur at least once
//        (?=.*[@#$%^&+=])  # a special character must occur at least once
//        (?=\S+$)          # no whitespace allowed in the entire string
//                .{8,}             # anything, at least eight places though
//        $                 # end-of-string
        super("please input password At Least one digi , lower case , upper case, special character , 8 charactor and no white space ");
    }
}
