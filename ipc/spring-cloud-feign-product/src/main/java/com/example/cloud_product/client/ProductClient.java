package com.example.cloud_product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.cloud_product.dto.Product;

@FeignClient(name = "product-server", url = "http://localhost:9090")
public interface ProductClient {
  @GetMapping("/api/products/{id}")
  Product getProduct(@PathVariable("id") String id);
}
