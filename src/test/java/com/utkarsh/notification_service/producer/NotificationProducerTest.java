package com.utkarsh.notification_service.producer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationMessage;
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

        NotificationMessage message = new NotificationMessage();
        message.setType(NotificationType.EMAIL);
        message.setRecipient("utkarsh@gmail.com");
        message.setSubject("Test Subject");
        message.setMessage("Test Email");

        notificationProducer.publish(message);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                RabbitMQConstants.EMAIL_ROUTING_KEY,
                message
        );
    }

    @Test
    void shouldPublishSmsNotification() {

        NotificationMessage message = new NotificationMessage();
        message.setType(NotificationType.SMS);
        message.setRecipient("9876543210");
        message.setSubject("");
        message.setMessage("Test SMS");

        notificationProducer.publish(message);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                RabbitMQConstants.SMS_ROUTING_KEY,
                message
        );
    }

    @Test
    void shouldPublishPushNotification() {

        NotificationMessage message = new NotificationMessage();
        message.setType(NotificationType.PUSH);
        message.setRecipient("user123");
        message.setSubject("");
        message.setMessage("Test Push");

        notificationProducer.publish(message);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                RabbitMQConstants.PUSH_ROUTING_KEY,
                message
        );
    }
}