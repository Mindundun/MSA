package com.example.cloud_product.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.Logger;

@Configuration
@EnableFeignClients(basePackages = "com.example.cloud_product.client")
public class ProductConfig {
    
    @Bean
    Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    RequestInterceptor requestInterceptor() {
        return (restTemplate) -> {
        restTemplate.header("Content-Type", "application/json");
        };
    }
}
