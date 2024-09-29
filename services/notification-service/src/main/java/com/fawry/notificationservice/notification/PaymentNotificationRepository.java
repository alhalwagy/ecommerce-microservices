package com.fawry.notificationservice.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentNotificationRepository extends JpaRepository<PaymentNotification, Long> {}
