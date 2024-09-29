package org.example.productsservice.product.dto;

import lombok.Builder;

@Builder
public record ProductResponse(
    Long id,
    String name,
    String description,
    double availableQuantity,
    double price,
    Long categoryId,
    String categoryName,
    String categoryDescription
){};
