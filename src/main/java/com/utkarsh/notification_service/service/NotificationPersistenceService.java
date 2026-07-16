package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.entity.Notification;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationPersistenceService {

    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification) {

        notification.setStatus(NotificationStatus.PENDING);
        notification.setRetryCount(0);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    public Notification updateStatus (Notification notification,
                                      NotificationStatus status) {

        notification.setStatus(status);
        notification.setUpdatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

}
