package org.example.orderservice.payment;

import org.example.orderservice.payment.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${spring.application.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping
    Long requestOrderPayment(@RequestBody PaymentRequest request);
}
