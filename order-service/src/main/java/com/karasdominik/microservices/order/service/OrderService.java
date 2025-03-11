package com.karasdominik.microservices.order.service;

import com.karasdominik.microservices.order.client.InventoryClient;
import com.karasdominik.microservices.order.dto.OrderRequest;
import com.karasdominik.microservices.order.event.OrderPlacedEvent;
import com.karasdominik.microservices.order.model.Order;
import com.karasdominik.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orders;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isInStock) {
            var order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orders.save(order);

            // Send the message to Kafka Topic having orderNumber and email
            var event = new OrderPlacedEvent();
            event.setOrderNumber(order.getOrderNumber());
            event.setEmail(orderRequest.userDetails().email());
            event.setFirstName(orderRequest.userDetails().firstName());
            event.setLastName(orderRequest.userDetails().lastName());
            log.info("Sending order placed event to Kafka");
            kafkaTemplate.send("order-placed", event);
            log.info("Order placed event sent successfully");
        } else {
            throw new RuntimeException("Product is out of stock");
        }
    }
}
