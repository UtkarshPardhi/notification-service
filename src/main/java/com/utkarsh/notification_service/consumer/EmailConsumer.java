package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailNotificationService emailNotificationService;

    @RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE, containerFactory = "emailRetryContainerFactory")
    public void consumeEmail(NotificationRequest request) {

        emailNotificationService.send(request);
    }
}