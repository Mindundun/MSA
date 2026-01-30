package com.example.welfare.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.example.welfare.service.WelfareService;
import com.example.point.dto.PointHistoryDto;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WelfareController {
    private final WelfareService welfareService;

    @GetMapping("/members/{id}/balance")
    public ResponseEntity<Long> getBalance(@PathVariable Long id){
        Long balance = welfareService.getBalance(id);
        return ResponseEntity.ok(balance);
    }
    
    @GetMapping("/members/{id}/history")
    public ResponseEntity<List<PointHistoryDto>> getHistory(@PathVariable Long id){
        List<PointHistoryDto> history = welfareService.getHistory(id);
        return ResponseEntity.ok(history);
    }
}


