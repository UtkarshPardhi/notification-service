package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.EmailNotificationService;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailNotificationService emailNotificationService;
    private final NotificationPersistenceService persistenceService;

    @RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE, containerFactory = "emailRetryContainerFactory")
    public void consumeEmail(NotificationRequest request) {

        try {

            emailNotificationService.send(request);

            persistenceService.updateStatus(
                    request.getNotificationId(),
                    NotificationStatus.SENT);

        } catch (Exception ex) {

            persistenceService.updateStatus(
                    request.getNotificationId(),
                    NotificationStatus.FAILED);

            throw ex; //Important : RabbitMQ Retry + DLQ
        }
    }
}