package com.fawry.notificationservice.notification;

import com.fawry.notificationservice.kafka.payment.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Order_Notification")
public class OrderConfirmNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime notificationDate;


  private String orderReference;
  private double totalAmount;

  private String paymentMethod;

  private Long customerId;

  @Transient private List<Long> products;

  public void addProduct(Long productId) {
    if (products == null) {
      products = new ArrayList<>();
    }
    products.add(productId);
  }
}
