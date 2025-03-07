package com.karasdominik.microservices.product.controller;

import com.karasdominik.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll().stream()
                .map(ProductResponse::of)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        return ProductResponse.of(service.create(request));
    }
}
