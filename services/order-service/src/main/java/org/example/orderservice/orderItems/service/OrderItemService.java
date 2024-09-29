package org.example.orderservice.orderItems.service;

import org.example.orderservice.orderItems.dto.OrderItemRequest;
import org.example.orderservice.orderItems.dto.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    Long createOrderItem(OrderItemRequest orderItemRequest);

    List<OrderItemResponse> findAllByOrderId(Long orderId);
}
