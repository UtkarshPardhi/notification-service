package com.utkarsh.notification_service.dto;

import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private NotificationType type;
    
    private String recipient;

    private String subject;

    private String message;

    private NotificationStatus status;

    private Integer retryCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
