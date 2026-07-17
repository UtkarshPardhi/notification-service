package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import com.utkarsh.notification_service.service.SmsNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsConsumer {

    private final SmsNotificationService smsNotificationService;
    private final NotificationPersistenceService persistenceService;

    @RabbitListener(queues = RabbitMQConstants.SMS_QUEUE, containerFactory = "smsRetryContainerFactory")
    public void consumeSms(NotificationMessage message) {

        try {

            smsNotificationService.send(message);

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
