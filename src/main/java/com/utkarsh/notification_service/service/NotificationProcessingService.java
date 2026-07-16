package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.dto.NotificationResponse;

import java.util.List;

public interface NotificationProcessingService {

    void send(NotificationRequest request);

    List<NotificationResponse> getAllNotifications();

}
