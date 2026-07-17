package com.utkarsh.notification_service.dto;

import com.utkarsh.notification_service.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {

    private Long notificationId;

    private NotificationType type;

    private String recipient;

    private String subject;

    private String message;

}
