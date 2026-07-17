package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import com.utkarsh.notification_service.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PushConsumer {

    private final PushNotificationService pushNotificationService;
    private final NotificationPersistenceService persistenceService;

    @RabbitListener(queues = RabbitMQConstants.PUSH_QUEUE, containerFactory = "pushRetryContainerFactory")
    public void consumePush(NotificationMessage message) {

        try {

            pushNotificationService.send(message);

            persistenceService.updateStatus(
                    message.getNotificationId(),
                    NotificationStatus.SENT);
        } catch (Exception ex) {

            persistenceService.updateStatus(
                    message.getNotificationId(),
                    NotificationStatus.FAILED);

            throw ex;
        }
    }

}
