package com.jeido.exercisespring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GeneralExecptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<Map<String, String>> errors = e.getFieldErrors().stream().map(fieldError -> {
            Map<String, String> map = new HashMap<>();
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
            return map;
        }).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
    }

}
