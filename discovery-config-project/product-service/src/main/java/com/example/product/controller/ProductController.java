package com.example.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 나는 새로고침 되는 Bean이야! [POST]http://localhost:9091/actuator/refresh 요청 시 이 Bean의 프로퍼티들을 다시 읽어옴
public class ProductController {

    // GitHub 내 product-service.yml의 메시지 프로퍼티와 매핑
    @Value("${message.owner:기본 박민둔 짱짱짱}")
    private String owner;

    @Value("${message.content:기본 내용 짱짱짱}")
    private String content;

    @GetMapping("/message")
    public String getMessage() {
        return "Owner: " + owner + ", Content: " + content;
    }

}
