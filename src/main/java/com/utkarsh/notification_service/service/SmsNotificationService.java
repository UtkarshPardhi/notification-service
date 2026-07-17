package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsNotificationService implements NotificationService {

    @Override
    public void send(NotificationRequest request) {

        log.info("==============================");
        log.info("Sending SMS...");
        log.info("Mobile :{}", request.getRecipient());
        log.info("Message : {}", request.getMessage());
        log.info("==============================");

        // Uncomment to test Retry & DLQ
        //throw new RuntimeException("SMS Gateway Down");
    }
}
