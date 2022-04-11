package com.example.home_task_spring.controller;

import com.example.home_task_spring.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankBookExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    public ErrorDto handleBankBookException(RuntimeException exception) {
        return ErrorDto.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .build();
    }
}
