package org.example.orderservice.orderItems.controller;


import lombok.RequiredArgsConstructor;
import org.example.orderservice.orderItems.dto.OrderItemResponse;
import org.example.orderservice.orderItems.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService service;

    @GetMapping("/orders/{order-id}")
    public ResponseEntity<List<OrderItemResponse>> findByOrderId(
            @PathVariable("order-id") Long orderId
    ) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}
