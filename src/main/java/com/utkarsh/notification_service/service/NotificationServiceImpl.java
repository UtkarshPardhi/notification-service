package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.dto.NotificationResponse;
import com.utkarsh.notification_service.entity.Notification;
import com.utkarsh.notification_service.mapper.NotificationMapper;
import com.utkarsh.notification_service.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationProcessingService {

    private final NotificationPersistenceService persistenceService;
    private final NotificationProducer producer;

    @Override
    public void send(NotificationRequest request) {

        Notification notification = NotificationMapper.toEntity(request);

        Notification savedNotification = persistenceService.save(notification);

        // Database generated ID to RabbitMQ message

//        request.setNotificationId(savedNotification.getId());
        NotificationMessage message =
                NotificationMapper.toMessage(savedNotification);

        producer.publish(message);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {

        return persistenceService.findAll()
                .stream()
                .map(NotificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
