package com.example.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RefreshScope // 나는 새로고침 되는 Bean이야! [POST]http://localhost:9092/actuator/refresh 요청 시 이 Bean의 프로퍼티들을 다시 읽어옴
public class OrderController {

    // GitHub 내 order-service.yml의 메시지 프로퍼티와 매핑
    @Value("${server.port}")
    private String port;


    @GetMapping("/port")
    public String getMessage() {
        return "Port: " + port;
    }

}
