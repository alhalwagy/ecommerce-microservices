package com.fawry.notificationservice.notification;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fawry.notificationservice.email.EmailService;
import com.fawry.notificationservice.kafka.order.OrderConfirmation;
import com.fawry.notificationservice.kafka.payment.PaymentNotificationRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

  private final PaymentNotificationRepository paymentNotificationRepository;
  private final OrderConfirmNotificationRepository confirmNotificationRepository;
  private final OrderConfirmNotificationRepository orderConfirmNotificationRepository;
  private final EmailService emailService;

  @KafkaListener(topics = "payment-topic", groupId = "my-group")
  public void consumePaymentSuccessNotifications(
      @Payload PaymentNotificationRequest paymentNotificationRequest) throws MessagingException {
    System.out.println(paymentNotificationRequest.getAmount());
    log.info(
        format(
            "Consuming the message from payment-topic Topic:: %s",
            paymentNotificationRequest));

    paymentNotificationRepository.save(
        PaymentNotification.builder()
            .notificationDate(LocalDateTime.now())
            .amount(paymentNotificationRequest.getAmount())
            .customerEmail(paymentNotificationRequest.getCustomerEmail())
            .customerFirstname(paymentNotificationRequest.getCustomerFirstname())
            .customerLastname(paymentNotificationRequest.getCustomerLastname())
            .orderReference(paymentNotificationRequest.getOrderReference())
            .paymentMethod(paymentNotificationRequest.getPaymentMethod().name())
            .build());
}
//    var customerName =
//        paymentNotificationRequest.getCustomerFirstname()
//            + " "
//            + paymentNotificationRequest.getCustomerLastname();
//    emailService.sendPaymentSuccessEmail(
//        paymentNotificationRequest.getCustomerEmail(),
//        customerName,
//        paymentNotificationRequest.getAmount(),
//        paymentNotificationRequest.getOrderReference());
//  }

//    @KafkaListener(topics = "order-topic", groupId = "my-group")
//    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation)
//        throws MessagingException {
//      log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
//      orderConfirmNotificationRepository.save(
//          OrderConfirmNotification.builder()
//                  .notificationDate(LocalDateTime.now())
//                  .products(orderConfirmation.products().stream().map(product ->(long) product.productId()).toList())
//                  .customerId(orderConfirmation.customer().id())
//              .build());

//      var customerName =
//          orderConfirmation.customer().firstname() + " " +
//   orderConfirmation.customer().lastname();
//      emailService.sendOrderConfirmationEmail(
//          orderConfirmation.customer().email(),
//          customerName,
//          orderConfirmation.totalAmount(),
//          orderConfirmation.orderReference(),
//          orderConfirmation.products());
//    }

}
