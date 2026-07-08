package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.service.SmsNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsConsumer {

    private final SmsNotificationService smsNotificationService;

    @RabbitListener(queues = RabbitMQConstants.SMS_QUEUE)
    public void consumeSms(NotificationRequest request) {

        smsNotificationService.send(request);

    }

}
