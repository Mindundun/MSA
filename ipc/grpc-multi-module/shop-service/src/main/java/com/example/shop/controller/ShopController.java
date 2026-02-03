package com.example.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.inventory.InventoryServiceGrpc;
import com.example.common.inventory.ProductRequest;
import com.example.common.inventory.StockResponse;

import net.devh.boot.grpc.client.inject.GrpcClient;

// HTTP/1.1 REST 요청을 받지만, 내부적으로는 HTTP/2 gRPC 고속 통신을 수행합니다.

@RestController
public class ShopController {

    // @GrpcClient: application.yml에 정의된 inventory-service 설정을 주입받음
    // BlockingStub: 동기(Synchronous) 방식의 클라이언트
    @GrpcClient("inventory-service")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub;

    @GetMapping("/shop/{productId}")
    public String checkProductStock(@PathVariable String productId) {
        
        // 1. gRPC 요청 객체 생성
        ProductRequest request = ProductRequest.newBuilder()
        .setProductId(productId)
        .build();

        // 2. gRPC 호출 (마치 로컬 메서드 호출하듯이 사용)
        // checkStock 메서드의 매개변수는 요청 객체만 전달. responseObserver는 내부적으로 처리됨
        StockResponse response = inventoryStub.checkStock(request);

        // 3. 결과 반환
        return String.format("상품: %s, 재고: %d, 판매가능: %s", 
        response.getProductId(), response.getQuantity(), response.getInStock());
    }
}