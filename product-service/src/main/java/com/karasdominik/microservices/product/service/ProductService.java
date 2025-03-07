package com.karasdominik.microservices.product.service;

import com.karasdominik.microservices.product.controller.CreateProductRequest;
import com.karasdominik.microservices.product.model.Product;
import com.karasdominik.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository products;

    public Product create(CreateProductRequest request) {
        log.info("Creating product");
        var product = products.save(Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build());
        log.info("Product created successfully");
        return product;
    }

    public List<Product> getAll() {
        return products.findAll();
    }
}
