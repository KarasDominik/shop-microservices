package com.karasdominik.microservices.product.repository;

import com.karasdominik.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}
