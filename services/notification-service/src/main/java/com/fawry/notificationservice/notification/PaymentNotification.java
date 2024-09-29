package com.fawry.notificationservice.notification;

import com.fawry.notificationservice.kafka.order.OrderConfirmation;
import com.fawry.notificationservice.kafka.payment.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payment_Notification")
public class PaymentNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime notificationDate;

  private String orderReference;
  private double amount;
  private String paymentMethod;
  private String customerFirstname;
  private String customerLastname;
  private String customerEmail;
}