package com.time.axis.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // 处理认证失败异常
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleAuthException(SecurityException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"code\": 401, \"message\": \"" + ex.getMessage() + "\"}");
    }
    
    // 处理404异常
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"code\": 404, \"message\": \"Resource not found\"}");
    }
}