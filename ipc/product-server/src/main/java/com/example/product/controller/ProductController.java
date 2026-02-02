package com.example.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.Product;
import com.example.product.exception.ProductNotFoundException;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private List<Product> products;

  @PostConstruct
  public void init() {
    products = new ArrayList<>();
    products.add(new Product(1L, "홈런볼"));
    products.add(new Product(2L, "웨하스"));
    products.add(new Product(3L, "맛동산"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable String id) {
    if (id == null || id.isBlank()) {
      throw new ProductNotFoundException("제품ID 필요");
    }
    Long productId = Long.parseLong(id);
    int index = products.indexOf(new Product(productId, null));
    if (index == -1) {
      throw new ProductNotFoundException("제품ID: " + id + " 조회 불가");
    }
    return ResponseEntity.ok(products.get(index));
  }
}
