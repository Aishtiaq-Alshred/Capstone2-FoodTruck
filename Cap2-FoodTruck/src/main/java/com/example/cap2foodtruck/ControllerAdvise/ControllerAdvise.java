package com.example.cap2foodtruck.ControllerAdvise;

import com.example.cap2foodtruck.ApiResponse.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}