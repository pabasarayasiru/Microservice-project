package com.pabasara.microservice.product_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")

public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}

