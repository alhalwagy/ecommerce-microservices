package org.example.orderservice.orders.dao;

import org.example.orderservice.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
