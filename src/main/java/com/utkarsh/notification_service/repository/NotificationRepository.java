package com.utkarsh.notification_service.repository;

import com.utkarsh.notification_service.entity.Notification;
import com.utkarsh.notification_service.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatus (NotificationStatus status);

}
