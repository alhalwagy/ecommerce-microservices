package org.example.orderservice.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.example.orderservice.payment.PaymentMethod;

@JsonInclude(Include.NON_EMPTY)
public record OrderResponse(
        Long id,
        String reference,
        double totalAmount,
        PaymentMethod paymentMethod,
        String customerId
) {

}
