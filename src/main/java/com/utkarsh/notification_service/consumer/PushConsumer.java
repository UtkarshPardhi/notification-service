package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PushConsumer {

    private final PushNotificationService pushNotificationService;

    @RabbitListener(queues = RabbitMQConstants.PUSH_QUEUE)
    public void consumePush(NotificationRequest request) {

        pushNotificationService.send(request);

    }

}
