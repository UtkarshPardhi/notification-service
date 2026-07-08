package com.utkarsh.notification_service.service;

import com.utkarsh.notification_service.dto.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest request);

}
