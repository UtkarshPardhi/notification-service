package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PushNotificationService implements NotificationService {

    @Override
    public void send(NotificationRequest request) {

        log.info("==============================");
        log.info("Sending Push Notification...");
        log.info("User :{}", request.getRecipient());
        log.info("Message : {}", request.getMessage());
        log.info("==============================");
    }
}
