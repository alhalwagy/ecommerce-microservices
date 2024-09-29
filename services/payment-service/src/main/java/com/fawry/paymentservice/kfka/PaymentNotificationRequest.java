package com.fawry.paymentservice.kfka;

import com.fawry.paymentservice.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentNotificationRequest implements Serializable {
  private String orderReference;
  private double amount;
  private String paymentMethod;
  private String customerFirstname;
  private String customerLastname;
  private String customerEmail;
}
