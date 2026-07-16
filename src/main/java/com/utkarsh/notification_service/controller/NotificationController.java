package com.utkarsh.notification_service.controller;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.producer.NotificationProducer;
import com.utkarsh.notification_service.response.ApiResponse;
import com.utkarsh.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notifications")
@RequiredArgsConstructor
public class NotificationController {

//    private final NotificationProducer producer;
       private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> publish(
            @Valid @RequestBody NotificationRequest request) {

//        producer.publish(request);
           notificationService.send(request);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Notification Published Successfully")
                .data("Message sent to RabbitMQ")
                .build();

        return ResponseEntity.ok(response);
    }
}
