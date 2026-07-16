package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.entity.Notification;
import com.utkarsh.notification_service.mapper.NotificationMapper;
import com.utkarsh.notification_service.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationPersistenceService persistenceService;
    private final NotificationProducer producer;

    @Override
    public void send(NotificationRequest request) {

        Notification notification = NotificationMapper.toEntity(request);

        Notification savedNotification = persistenceService.save(notification);

        producer.publish(request);
    }
}
