package org.example.orderservice.products.dto;

public record PurchaseResponse(
        Long productId,
        String name,
        String description,
        double price,
        double quantity
) {
}