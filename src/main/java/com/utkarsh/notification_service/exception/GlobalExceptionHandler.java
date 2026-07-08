package com.utkarsh.notification_service.exception;

import com.utkarsh.notification_service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiResponse<Map<String, String>> response =
                ApiResponse.<Map<String, String>> builder()
                        .success(false)
                        .message("Validation Failed")
                        .data(errors)
                        .build();

        return ResponseEntity.badRequest().body(response);
    }

    // Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ApiResponse<Map<String, String>>> handleResourceNotFound(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
                );

        ApiResponse<Map<String, String>> response =
                ApiResponse.<Map<String, String>>builder()
                             .success(false)
                             .message("Validation Failed")
                             .data(errors)
                             .build();

        return ResponseEntity.badRequest().body(response);
    }

    // Any Other Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(
            Exception ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
