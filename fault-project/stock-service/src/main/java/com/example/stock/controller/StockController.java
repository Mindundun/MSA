package com.example.stock.controller;

import com.example.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping("/{ticker}")
    public String checkStockPrice(@PathVariable String ticker) {
        return stockService.getStockPrice(ticker);
    }
}