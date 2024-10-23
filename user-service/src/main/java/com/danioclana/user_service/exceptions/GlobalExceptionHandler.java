package com.danioclana.user_service.exceptions;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.ServletException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
            String errorMessage = ex.getMessage();

            String[] partes = errorMessage.split(", problem: ");
            if (partes.length > 1) {
                errorMessage = partes[1].trim(); 
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        @ExceptionHandler(IOException.class)
        public ResponseEntity<String> handleIOException(IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro de I/O: " + ex.getMessage());
        }

        @ExceptionHandler (ServletException.class)
        public ResponseEntity<String> handleServletException(ServletException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro de Servlet: " + ex.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

}
