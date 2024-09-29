package org.example.orderservice.orderItems.dto;

public record OrderItemRequest(
        Long id,
        Long orderId,
        Long productId,
        double quantity
) {
}
