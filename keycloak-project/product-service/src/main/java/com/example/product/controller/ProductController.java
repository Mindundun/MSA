package com.example.product.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 가장 많이 사용하는 사용자 정보
// 1. sub (Subject)
//   1) 사용자의 고유 식별자(UUID)
//   2) 예: f5a3b2-1234-5678-abcd-1234567890ab
//   3) DB의 Primary Key로 사용하기 가장 좋은 값입니다. (이름이나 이메일은 변경될 수 있지만, 이 값은 절대 변하지 않습니다.)
// 2. preferred_username
//   1) 사용자가 로그인할 때 입력한 ID입니다.
//   2) 예: user1
// 3. email
//   1) 사용자의 이메일 주소입니다.
//   2) 예: user1@example.com
// 4. email_verified
//   1) 이메일 인증 여부입니다. (Boolean)
//   2) 예: true / false

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts(@AuthenticationPrincipal Jwt jwt) {
        return "Product List (User: " + jwt.getClaimAsString("preferred_username") + ")";
    }
}