package com.pabasara.microservice.order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pabasara.microservice.order.client.InventoryClient;
import com.pabasara.microservice.order.dto.OrderRequest;
import com.pabasara.microservice.order.model.Order;
import com.pabasara.microservice.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder( OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());

            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode"+ orderRequest.skuCode() +" is not in stock");
        }
        
    }
}
