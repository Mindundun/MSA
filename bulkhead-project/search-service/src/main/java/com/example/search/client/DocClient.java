package com.example.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "doc-server", url = "http://localhost:8081")
public interface DocClient {
  
    @GetMapping("/api/docs/search")
    String search();
}