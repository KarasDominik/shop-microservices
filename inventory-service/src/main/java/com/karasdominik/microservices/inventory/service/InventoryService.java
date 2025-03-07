package com.karasdominik.microservices.inventory.service;

import com.karasdominik.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventory;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventory.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
