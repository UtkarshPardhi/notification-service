package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Component
//@RequiredArgsConstructor
//public class EmailConsumer {
//
//    private final EmailNotificationService emailNotificationService;
//    private final RabbitTemplate rabbitTemplate;
//
//    @RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE)
//    public void consumeEmail(NotificationRequest request, Message message) {
//
//        int retryCount = getRetryCount(message);
//        try {
//
//            emailNotificationService.send(request);
//
//        } catch (Exception ex) {
//
//            if (retryCount >= RabbitMQConstants.MAX_RETRY_COUNT) {
//
//                rabbitTemplate.convertAndSend(
//                        RabbitMQConstants.DLX_EXCHANGE,
//                        RabbitMQConstants.EMAIL_DLQ_ROUTING_KEY,
//                        request
//                );
//
//                return;
//            }
//
//            throw ex;
//        }
//    }
//
//    @SuppressWarnings("uncheked")
//    private int getRetryCount(Message message) {
//
//        Object xDeath = message.getMessageProperties()
//                .getHeaders()
//                .get("x-death");
//
//        if (xDeath == null) {
//            return 0;
//        }
//
//        List<Map<String, Object>> deaths =
//                (List<Map<String, Object>>) xDeath;
//
//        for (Map<String, Object> death : deaths) {
//
//            if (RabbitMQConstants.EMAIL_QUEUE.equals(death.get("queue"))) {
//
//                Number count = (Number) death.get("count");
//                return count.intValue();
//            }
//        }
//
//        return 0;
//    }
//
//}
@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailNotificationService emailNotificationService;

    @RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE, containerFactory = "emailRetryContainerFactory")
    public void consumeEmail(NotificationRequest request) {

        emailNotificationService.send(request);
    }
}