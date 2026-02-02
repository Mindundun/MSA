package com.example.cloud_product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud_product.client.ProductClient;

import lombok.RequiredArgsConstructor;
import com.example.cloud_product.dto.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OpenFeignController {

  private final ProductClient productClient;

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable String id) {
    return ResponseEntity.ok(productClient.getProduct(id));
  }
}