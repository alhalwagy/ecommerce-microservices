package com.fawry.paymentservice.rest;

import com.fawry.paymentservice.dto.PaymentRequest;
import com.fawry.paymentservice.entities.Payment;
import com.fawry.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @GetMapping
  public List<Payment> getPayments() {
    return paymentService.getAllPayments();
  }

  @PostMapping
  public Long createPayment(@RequestBody PaymentRequest request) {
    return paymentService.createPayment(request).getId();
  }
}
