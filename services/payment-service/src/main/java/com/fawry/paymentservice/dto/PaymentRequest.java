package com.fawry.paymentservice.dto;

import com.fawry.paymentservice.entities.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        double amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}