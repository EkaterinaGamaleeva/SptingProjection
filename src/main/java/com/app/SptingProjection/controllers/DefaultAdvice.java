package com.app.SptingProjection.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class DefaultAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        return new ResponseEntity<>(ErrorResponse.create(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

}