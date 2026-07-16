package com.utkarsh.notification_service.mapper;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.entity.Notification;
import com.utkarsh.notification_service.enums.NotificationStatus;

import java.time.LocalDateTime;

public final class NotificationMapper {

    private NotificationMapper() {
    }

    public static Notification toEntity(NotificationRequest request) {

        return Notification.builder()
                .type(request.getType())
                .recipient(request.getRecipient())
                .subject(request.getSubject())
                .message(request.getMessage())
                .status(NotificationStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
