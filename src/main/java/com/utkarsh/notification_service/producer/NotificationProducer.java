package com.utkarsh.notification_service.producer;


import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private static final Logger log =
            LoggerFactory.getLogger(NotificationProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public void publish(NotificationRequest request) {

        String routingKey = switch (request.getType()) {
            case EMAIL -> RabbitMQConstants.EMAIL_ROUTING_KEY;
            case SMS -> RabbitMQConstants.SMS_ROUTING_KEY;
            case PUSH -> RabbitMQConstants.PUSH_ROUTING_KEY;
        };

        rabbitTemplate.convertAndSend(
                RabbitMQConstants.NOTIFICATION_EXCHANGE,
                routingKey,
                request
        );

        log.info("Message Published : {}", request);
    }
}
