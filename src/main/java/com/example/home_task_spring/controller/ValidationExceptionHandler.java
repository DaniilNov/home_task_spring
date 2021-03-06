package com.example.home_task_spring.controller;

import com.example.home_task_spring.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    String field = ((FieldError) objectError).getField();
                    return field + ": " + objectError.getDefaultMessage();
                }).collect(Collectors.joining("; "));

        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .message(errorMessage)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException e) {
        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build();
    }
}
