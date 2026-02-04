package com.example.search.service;

import org.springframework.stereotype.Service;

import com.example.search.client.DocClient;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final DocClient docClient;

    // Bulkhead 적용: type = SEMAPHORE (기본값. 생략 가능)
    @Bulkhead(
        name = "docService", 
        type = Bulkhead.Type.SEMAPHORE, 
        fallbackMethod = "searchFallback"
        )
    public String searchDocuments() {
        log.info("Try to searchDocuments!");
        String result = docClient.search();  // doc-server에 요청 (3초 지연)
        log.info("Exiting searchDocuments!");
        return result;
    }

    // Fallback: 동시 허용량 초과 시 실행
    public String searchFallback(Throwable t) {
        if (t instanceof BulkheadFullException) {
            log.error("Bulkhead Full! Request Rejected.");
            return "[Fallback] Server is busy. Please try later.";
        }
        return "[Fallback] Unexpected Error: " + t.getMessage();
    }
}