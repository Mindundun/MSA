package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http
      // 1. CSRF 비활성화
      // CSRF는 브라우저의 "세션 쿠키 자동 전송" 특징을 이용한 공격입니다.
      // REST API는 Stateless하게 동작하며, 쿠키 대신 헤더(Authorization)를 사용하므로 CSRF 방어가 불필요합니다.
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      
      // 2. 요청별 권한 설정 (Authorization)
      .authorizeExchange(exchanges -> exchanges
        // Gateway로 들어오는 모든 요청(.anyExchange)에 대해 인증된 사용자만 접근 허용(.authenticated)
        // 만약 특정 경로(예: /login, /public)를 열어주려면 .pathMatchers("/public/**").permitAll()을 먼저 선언해야 합니다.
        .anyExchange().authenticated()
      )
      
      // 3. OAuth2 Resource Server 설정
      // 시큐리티가 JWT 토큰을 가로챕니다.
      // 그런 다음 JWT 토큰을 검증하는 역할을 수행합니다. (application.yml의 issuer-uri, jwk-set-uri 설정 사용)
      // 1) 서명(Signature) 검증: Keycloak의 공개키(JWK)를 이용해 이게 진짜 Keycloak이 발급한 게 맞는지 확인.
      // 2) 만료(Expiration) 검증: 토큰의 유효 기간 검증 시 확인.
      .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(Customizer.withDefaults())
      );

	  return http.build();
  }
}