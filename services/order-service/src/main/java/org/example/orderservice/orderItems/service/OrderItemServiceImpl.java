package org.example.orderservice.orderItems.service;


import lombok.RequiredArgsConstructor;
import org.example.orderservice.orderItems.dao.OrderItemRepository;
import org.example.orderservice.orderItems.dto.OrderItemMapper;
import org.example.orderservice.orderItems.dto.OrderItemRequest;
import org.example.orderservice.orderItems.dto.OrderItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{
    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;

    public Long createOrderItem(OrderItemRequest request) {
        var order = mapper.toOrderItem(request);
        return repository.save(order).getId();
    }

    public List<OrderItemResponse> findAllByOrderId(Long orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }
}
