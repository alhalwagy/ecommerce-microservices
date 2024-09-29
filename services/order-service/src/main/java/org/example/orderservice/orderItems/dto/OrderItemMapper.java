package org.example.orderservice.orderItems.dto;

import org.example.orderservice.orderItems.OrderItem;
import org.example.orderservice.orders.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderItemMapper {
    public OrderItem toOrderItem(OrderItemRequest orderItemRequest) {
        return OrderItem
                .builder().
                quantity(orderItemRequest.quantity()).
                productId(orderItemRequest.productId()).
                order(Order.builder().id(orderItemRequest.orderId()).build()).
                build();
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getId(), orderItem.getQuantity());
    }

}
