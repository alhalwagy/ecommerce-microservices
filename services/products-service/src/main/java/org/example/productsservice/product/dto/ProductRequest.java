package org.example.productsservice.product.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public record ProductRequest(
        Long id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity must be positive")
        double availableQuantity,
        @Positive(message = "Price must be positive")
        double price,
        @NotNull(message = "Product category is required")
        Long categoryId
) {
}
