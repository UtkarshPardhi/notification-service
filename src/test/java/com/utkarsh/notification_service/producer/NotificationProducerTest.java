package com.utkarsh.notification_service.producer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private NotificationProducer notificationProducer;

    @Test
    void shouldPublishEmailNotification() {

        NotificationRequest request = new NotificationRequest();
        request.setType(NotificationType.EMAIL);
        request.setRecipient("utkarsh@gmail.com");
        request.setSubject("Test Subject");
        request.setMessage("Test Email");

        notificationProducer.publish(request);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                RabbitMQConstants.EMAIL_ROUTING_KEY,
                request
        );
    }

    @Test
    void shouldPublishSmsNotification() {

        NotificationRequest request = new NotificationRequest();
        request.setType(NotificationType.SMS);
        request.setRecipient("9876543210");
        request.setSubject("");
        request.setMessage("Test SMS");

        notificationProducer.publish(request);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                RabbitMQConstants.SMS_ROUTING_KEY,
                request
        );
    }

    @Test
    void shouldPublishPushNotification() {

        NotificationRequest request = new NotificationRequest();
        request.setType(NotificationType.PUSH);
        request.setRecipient("user123");
        request.setSubject("");
        request.setMessage("Test Push");

        notificationProducer.publish(request);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                RabbitMQConstants.PUSH_ROUTING_KEY,
                request
        );
    }
}