package com.karasdominik.microservices.order.service;

import com.karasdominik.microservices.order.client.InventoryClient;
import com.karasdominik.microservices.order.dto.OrderRequest;
import com.karasdominik.microservices.order.model.Order;
import com.karasdominik.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orders;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isInStock) {
            var order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orders.save(order);
        } else {
            throw new RuntimeException("Product is out of stock");
        }
    }
}
