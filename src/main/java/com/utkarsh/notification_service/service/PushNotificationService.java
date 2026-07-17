package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PushNotificationService implements NotificationService {

    @Override
    public void send(NotificationMessage message) {

        log.info("==============================");
        log.info("Sending Push Notification...");
        log.info("User :{}", message.getRecipient());
        log.info("Message : {}", message.getMessage());
        log.info("==============================");

        // Uncomment to test Retry & DLQ
        // throw new RuntimeException("Push Server Down");
    }
}
