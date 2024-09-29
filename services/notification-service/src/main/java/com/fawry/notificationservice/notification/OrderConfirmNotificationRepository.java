package com.fawry.notificationservice.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderConfirmNotificationRepository
    extends JpaRepository<OrderConfirmNotification, Long> {

}
