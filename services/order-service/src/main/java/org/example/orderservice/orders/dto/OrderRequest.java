package org.example.orderservice.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.orderservice.payment.PaymentMethod;
import org.example.orderservice.products.dto.PurchaseRequest;

import java.util.List;

public record OrderRequest(
        Long id,
        String reference,
        @Positive(message = "Order amount should be positive")
        double amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        String customerId,
        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}
