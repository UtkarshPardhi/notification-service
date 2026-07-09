package com.utkarsh.notification_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationType;
import com.utkarsh.notification_service.producer.NotificationProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NotificationProducer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldPublishNotificationSuccessfully() throws Exception {

        NotificationRequest request = new NotificationRequest();
        request.setType(NotificationType.EMAIL);
        request.setRecipient("utkarsh@gmail.com");
        request.setSubject("Test");
        request.setMessage("Hello");

        mockMvc.perform(post("/api/notifications")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Notification Published Successfully"))
                .andExpect(jsonPath("$.data").value("Message sent to RabbitMQ"));

        verify(producer).publish(request);
    }

    @Test
    void shouldReturnBadRequestWhenRecipientIsMissing() throws Exception {

        NotificationRequest request = new NotificationRequest();
        request.setType(NotificationType.EMAIL);
        request.setSubject("Test");
        request.setMessage("Hello");

        mockMvc.perform(post("/api/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

}
