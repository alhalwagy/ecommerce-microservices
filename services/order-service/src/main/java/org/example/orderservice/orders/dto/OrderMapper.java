package org.example.orderservice.orders.dto;

import org.example.orderservice.orders.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest order){
        if(order == null){
            return null;
        }
        return Order.builder()
                .id(order.id())
                .reference(order.reference())
                .totalAmount(order.amount())
                .paymentMethod(order.paymentMethod())
                .customerId(order.customerId())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
