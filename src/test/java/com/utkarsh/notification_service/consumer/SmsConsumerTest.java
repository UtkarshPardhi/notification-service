package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.service.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SmsConsumerTest {

    @Mock
    private SmsNotificationService smsNotificationService;

    @InjectMocks
    private SmsConsumer smsConsumer;

    @Test
    void shouldConsumeSmsNotification() {

        NotificationRequest request = new NotificationRequest();
        request.setRecipient("9986492003");
        request.setSubject("");
        request.setMessage("Test SMS");

        smsConsumer.consumeSms(request);

        verify(smsNotificationService).send(request);
    }
}
