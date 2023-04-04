package com.example.group02_spring_mini_project001.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
     @ExceptionHandler(CategoryNotFoundException.class)
    ProblemDetail showCategoryNotFound(CategoryNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
     }

    @ExceptionHandler(BlankException.class)
    ProblemDetail Blank(BlankException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }
    @ExceptionHandler(EmailException.class)
    ProblemDetail Email(EmailException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(PasswordException.class)
    ProblemDetail Password(PasswordException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(InvalidStatusException.class)
    ProblemDetail handleInvalidStatusExceptiion(InvalidStatusException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("localhost:8080/errors/bad-request"));
        problemDetail.setTitle("Invalid field !!");
        problemDetail.setStatus(HttpStatus.BAD_REQUEST);
        return problemDetail;
    }

    @ExceptionHandler(NotFoundIDException.class)
    ProblemDetail handleNotFoundException(NotFoundIDException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("localhost:8080/errors/not-found"));
        problemDetail.setTitle("Task not found !!");
        return problemDetail;
    }

//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        Map<String, Object> responseBody = new LinkedHashMap<>();
//        responseBody.put("timestamp", new Date());
//        responseBody.put("status", status.value());
//
//        List<String> errors = ex.getBindingResult().getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        responseBody.put("errors", errors);
//
//        return new ResponseEntity<>(responseBody, headers, status);
//    }
}
