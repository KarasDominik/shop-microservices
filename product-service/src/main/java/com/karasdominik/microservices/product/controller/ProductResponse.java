package com.karasdominik.microservices.product.controller;

import com.karasdominik.microservices.product.model.Product;

import java.math.BigDecimal;

record ProductResponse(String id, String name, String description, BigDecimal price) {

    static ProductResponse of(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
