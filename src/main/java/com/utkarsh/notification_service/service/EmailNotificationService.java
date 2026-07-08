package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(NotificationRequest request) {

        log.info("==============================");
        log.info("Sending Email...");
        log.info("To :{}", request.getRecipient());
        log.info("Subject :{}", request.getSubject());
        log.info("Message : {}", request.getMessage());
        log.info("==============================");

        throw new RuntimeException("SMTP Server Down");

    }
}
