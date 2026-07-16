package com.utkarsh.notification_service.controller;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.dto.NotificationResponse;
import com.utkarsh.notification_service.producer.NotificationProducer;
import com.utkarsh.notification_service.response.ApiResponse;
import com.utkarsh.notification_service.service.NotificationProcessingService;
import com.utkarsh.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
@RequiredArgsConstructor
public class NotificationController {

//    private final NotificationProducer producer;
       private final NotificationProcessingService notificationProcessingService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> publish(
            @Valid @RequestBody NotificationRequest request) {

//        producer.publish(request);
           notificationProcessingService.send(request);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Notification Published Successfully")
                .data("Message sent to RabbitMQ")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getAllNotifications() {

        List<NotificationResponse> notifications =
                notificationProcessingService.getAllNotifications();

        ApiResponse<List<NotificationResponse>> response =
                ApiResponse.<List<NotificationResponse>>builder()
                        .success(true)
                        .message("Notification fetched successfully")
                        .data(notifications)
                        .build();

        return ResponseEntity.ok(response);
    }
}
