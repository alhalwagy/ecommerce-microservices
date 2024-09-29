package org.example.productsservice.product.dto;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        double quantity,
        double price
) {
}
