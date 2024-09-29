package com.fawry.paymentservice.kfka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaProducerConfig {

  @Bean
  public NewTopic paymentTopic() {
    return TopicBuilder.name("payment-topic").build();
  }

}
