package com.example.stock.service;

import com.example.stock.client.MarketClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final MarketClient marketClient;
    private static final String MARKET_SERVICE = "marketService";

    // Retry가 먼저 동작하고, 재시도 횟수 초과 시 CircuitBreaker에 실패로 기록됩니다.
    @Retry(name = MARKET_SERVICE, fallbackMethod = "getPriceFallback")
    @CircuitBreaker(name = MARKET_SERVICE)
    public String getStockPrice(String ticker) {
        log.info("Market Server 호출 with {}", ticker);
        return marketClient.getPrice(ticker);
    }

    // Fallback 메서드: 원본 메서드와 파라미터가 같아야 하고, 마지막에 Exception을 받습니다.
    public String getPriceFallback(String ticker, Throwable t) {
        log.error("Fallback 호출 with {}: {}", ticker, t.getMessage());
        // 요청 실패하여 임의로 기존 캐시 값을 날리는 것처럼 구성
        return "요청 실패로 직전 Price 안내: 90.00";
    }
}