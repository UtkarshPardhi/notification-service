package com.utkarsh.notification_service.dto;


import com.utkarsh.notification_service.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    // Id
    private Long notificationId;

    @NotNull(message = "Notification type is required")
    private NotificationType type;

    @NotBlank(message = "Recipient cannot be empty")
    private String recipient;

    private String subject;

    @NotBlank(message = "Message cannot be empty")
    private String message;

}
