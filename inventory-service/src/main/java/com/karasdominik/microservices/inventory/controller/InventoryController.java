package com.karasdominik.microservices.inventory.controller;

import com.karasdominik.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
class InventoryController {

    private final InventoryService service;

    @GetMapping
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return service.isInStock(skuCode, quantity);
    }
}
