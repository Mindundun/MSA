package com.example.admin.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Keycloak의 언어(JSON)를 Spring Security의 언어(Java Object)로 변환
// Keycloak에서 발급한 JWT 토큰 내부의 Role 정보를 Spring Security가 이해할 수 있는 권한 객체로 변환
// SecurityConfig에 등록시켜 사용

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // 1. JWT 내부의 'realm_access' 클레임 추출
        // Keycloak은 기본적으로 Realm 레벨의 역할을 'realm_access'라는 키 아래에 JSON 객체 형태로 담습니다.
        // 예: "realm_access": { "roles": ["admin", "user"] }
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        // 권한 정보가 없으면 빈 리스트 반환 (NullPointerException 방지)
        if (realmAccess == null || realmAccess.isEmpty()) {
        return List.of();
        }

        // 2. 'roles' 키에서 실제 역할 이름 리스트 추출
        List<String> roles = (List<String>) realmAccess.get("roles");

        // 3. 역할 이름을 GrantedAuthority 객체로 매핑 (접두사 'ROLE_' 추가)
        // Spring Security의 hasRole("admin") 메소드는 기본적으로 "ROLE_" 접두사를 기대합니다.
        // 따라서 "admin" -> "ROLE_admin"로 변환해주어야 합니다.
        return roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toList());
    }
}