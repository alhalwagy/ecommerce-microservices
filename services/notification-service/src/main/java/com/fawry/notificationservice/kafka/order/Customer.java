package com.fawry.notificationservice.kafka.order;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {

}