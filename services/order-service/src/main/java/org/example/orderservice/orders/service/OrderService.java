package org.example.orderservice.orders.service;

import org.example.orderservice.orders.dto.OrderRequest;
import org.example.orderservice.orders.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    /**
     * Create an order
     * @param orderRequest
     * @return
     */
    Long createOrder(OrderRequest orderRequest);

    /**
     * Retrive all orders
     * @return
     */
    List<OrderResponse> findAllOrders();

    /**
     * Find order by id and convert to OrderResponse
     * @param id
     * @return
     */
    OrderResponse findById(Long id);
}
