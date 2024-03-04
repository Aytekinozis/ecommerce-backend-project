package com.workintech.ecommerce.exceptions;

import com.workintech.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(ProductException productException){
        ExceptionResponse errorResponse = new ExceptionResponse(productException.getMessage(),productException.getHttpStatus());
        return new ResponseEntity<>(errorResponse, productException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(CategoryException categoryException){
        ExceptionResponse errorResponse = new ExceptionResponse(categoryException.getMessage(),categoryException.getHttpStatus());
        return new ResponseEntity<>(errorResponse, categoryException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(ImageException imageException){
        ExceptionResponse errorResponse = new ExceptionResponse(imageException.getMessage(),imageException.getHttpStatus());
        return new ResponseEntity<>(errorResponse, imageException.getHttpStatus());
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(Exception exception){
        ExceptionResponse errorResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
