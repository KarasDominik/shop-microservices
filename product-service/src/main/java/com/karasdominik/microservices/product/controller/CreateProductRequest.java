package com.karasdominik.microservices.product.controller;

import java.math.BigDecimal;

public record CreateProductRequest(String id, String name, String description, BigDecimal price) {}
