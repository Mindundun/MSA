package com.example.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

  private String errorCode;

  public ProductNotFoundException(String message) {
    super(message);
    this.errorCode = "PRODUCT_NOT_FOUND";
  }
}