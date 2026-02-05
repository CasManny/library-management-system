package com.casmanny.librarymanagementsystem.exception;

import com.casmanny.librarymanagementsystem.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ApiResponse> handleGenreNotFoundException(GenreNotFoundException ex) {
        ApiResponse response = ApiResponse.builder()
                .message(ex.getMessage())
                .status(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
