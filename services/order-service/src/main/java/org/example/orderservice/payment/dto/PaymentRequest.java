package org.example.orderservice.payment.dto;

import org.example.orderservice.customers.CustomerResponse;
import org.example.orderservice.payment.PaymentMethod;

public record PaymentRequest(
        double amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}