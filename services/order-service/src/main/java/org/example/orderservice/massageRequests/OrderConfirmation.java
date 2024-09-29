package org.example.orderservice.massageRequests;

import org.example.orderservice.customers.CustomerResponse;
import org.example.orderservice.payment.PaymentMethod;
import org.example.orderservice.products.dto.PurchaseResponse;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        double totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
