package com.fawry.paymentservice.kfka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
@RequiredArgsConstructor
public class MessageProducer {

  private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

  public void send(PaymentNotificationRequest request) {
    Message<PaymentNotificationRequest> message =
        MessageBuilder.withPayload(request).setHeader(TOPIC, "payment-topic").build();

    kafkaTemplate.send(message);
    System.out.println(message.getPayload());
    System.out.println("message has been sent successfully.............");
  }
}
