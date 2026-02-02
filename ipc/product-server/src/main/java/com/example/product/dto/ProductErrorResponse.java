package com.example.product.dto;

public record ProductErrorResponse(String message, String code) {
  // 정적 메소드 of
  public static ProductErrorResponse of(String message, String code) {
    return new ProductErrorResponse(message, code);
  }
}
