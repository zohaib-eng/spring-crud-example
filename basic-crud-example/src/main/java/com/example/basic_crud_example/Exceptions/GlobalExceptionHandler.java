package com.example.basic_crud_example.Exceptions;

import com.example.basic_crud_example.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handle Resource Not Found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.error(ex.getMessage(), "RESOURCE_NOT_FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<Map<String, String>>>> handleValidation(MethodArgumentNotValidException e) {
        List<Map<String, String>> details = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    Map<String, String> errorDetail = new HashMap<>();
                    errorDetail.put("field", ((org.springframework.validation.FieldError) error).getField());
                    errorDetail.put("message", error.getDefaultMessage());
                    return errorDetail;
                })
                .collect(Collectors.toList());

        ApiResponse.Meta meta = new ApiResponse.Meta(
                Instant.now(),
                UUID.randomUUID().toString(),
                "1.0"
        );

        ApiResponse<List<Map<String, String>>> response = new ApiResponse<>(
                false,
                "Validation error!",
                "VALIDATION_ERROR",
                details,
                meta
        );

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception exception) {
        log.error("Internal Server Error: {}", exception.getMessage(), exception);
        ApiResponse<Object> response = ApiResponse.error(exception.getMessage(), "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
