package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.entity.Notification;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public Notification updateStatus (Long notificationId,
                                      NotificationStatus status) {

        Notification notification = notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                            "Notification not found with Id : " + notificationId ));

        notification.setStatus(status);
        notification.setUpdatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public List<Notification> findByStatus(NotificationStatus status) {
        return notificationRepository.findByStatus(status);
    }

}
