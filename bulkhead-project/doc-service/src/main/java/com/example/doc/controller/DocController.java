package com.example.doc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docs")
public class DocController {

    @GetMapping("/search")
    public String searchDocs() throws InterruptedException {
        // 3초 지연 (리소스 점유 시뮬레이션)
        Thread.sleep(3000);
        return "Document Content Found";
    }
}
