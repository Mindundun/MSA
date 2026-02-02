package com.example.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.product.dto.ProductErrorResponse;

@RestControllerAdvice
public class ProductExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ProductErrorResponse> notFound(ProductNotFoundException e) {
    return ResponseEntity
      .status(404)
      .body(ProductErrorResponse.of(
        e.getMessage(), 
        e.getErrorCode()
      )
    );
  }
}