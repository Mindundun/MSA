package com.example.inventory.service;

import com.example.common.inventory.ProductRequest;
import com.example.common.inventory.StockResponse;
import com.example.common.inventory.InventoryServiceGrpc.InventoryServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService // 정보를 제공한다는 의미의 어노테이션
public class InventoryService extends InventoryServiceImplBase{
    @Override
    public void checkStock(ProductRequest request, StreamObserver<StockResponse> responObserver){
        String productId = request.getProductId();

        // productId : 999, 재고 없는 것으로 가정
        int stock = 100;

        if(productId.equals("999")){
            stock = 0;
        }

        // 메세지는 newBuilder()를 통해 생성, new StockResponse() 생성자 사용 불가
        StockResponse response = StockResponse.newBuilder()
                .setProductId(productId)
                .setQuantity(stock)
                .setInStock(stock > 0)
                .build();
        
        // 데이터 장착
        responObserver.onNext(response);
        
        // 완료 신호
        responObserver.onCompleted();
    }
}
