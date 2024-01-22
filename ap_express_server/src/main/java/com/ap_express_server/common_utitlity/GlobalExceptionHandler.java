package com.ap_express_server.common_utitlity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        // Create a custom response entity with the error code and message
        return ResponseEntity.badRequest().body(new CustomException(ex.getMessage(), ex.getErrorCode()));
    }

    // Add more exception handlers for different types of exceptions if needed
}
