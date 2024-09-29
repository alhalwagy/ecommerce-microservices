package com.fawry.paymentservice.mapper;

import com.fawry.paymentservice.dto.PaymentRequest;
import com.fawry.paymentservice.entities.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

  public Payment toPayment(PaymentRequest paymentRequest) {
    if (paymentRequest == null) {
      return null;
    }
    return Payment.builder()
        .paymentMethod(paymentRequest.paymentMethod())
        .amount(paymentRequest.amount())
        .orderId(paymentRequest.orderId())
        .createdDate(LocalDateTime.now())
        .build();
  }
}
