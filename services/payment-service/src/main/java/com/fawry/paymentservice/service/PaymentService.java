package com.fawry.paymentservice.service;

import com.fawry.paymentservice.dto.PaymentRequest;
import com.fawry.paymentservice.entities.Payment;
import com.fawry.paymentservice.kfka.MessageProducer;
import com.fawry.paymentservice.kfka.PaymentNotificationRequest;
import com.fawry.paymentservice.mapper.PaymentMapper;
import com.fawry.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository paymentRepository;
  private final PaymentMapper paymentMapper;
  private final MessageProducer messageProducer;

  public List<Payment> getAllPayments() {
    return paymentRepository.findAll();
  }

  public Payment createPayment(PaymentRequest request) {
    System.out.println(request.toString());
    Payment prestiencePayment = paymentMapper.toPayment(request);
    Payment newPayment = paymentRepository.save(prestiencePayment);

    PaymentNotificationRequest paymentNotificationRequest =
        new PaymentNotificationRequest(
            request.orderReference(),
            request.amount(),
            request.paymentMethod().name(),
            request.customer().firstname(),
            request.customer().lastname(),
            request.customer().email());
    messageProducer.send(paymentNotificationRequest);
    return newPayment;
  }
}
