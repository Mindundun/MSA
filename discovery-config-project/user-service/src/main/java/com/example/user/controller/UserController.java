package com.example.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RefreshScope // 나는 새로고침 되는 Bean이야! [POST]http://localhost:9091/actuator/busrefresh 요청 시 이 Bean의 프로퍼티들을 다시 읽어옴
@RequiredArgsConstructor
public class UserController {

    // 아래와 같이 처리시 매번 0으로 가져옴.
    // 왜냐하면 서버 포트는 애플리케이션 시작 시점에 정해지기 때문.
    // @Value("${server.port}") private String

    private final Environment env; // 환경 변수 담당

    @Value("${user.welcome.message:기본 환영 메시지}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public String getWelcomeMessage() {
        return String.format("Welcome User Message: %s : %s", env.getProperty("local.server.port"), welcomeMessage);
    }
}
