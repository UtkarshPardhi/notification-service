package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import com.utkarsh.notification_service.service.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SmsConsumerTest {

    @Mock
    private SmsNotificationService smsNotificationService;

    @Mock
    private NotificationPersistenceService persistenceService;

    @InjectMocks
    private SmsConsumer smsConsumer;

    @Test
    void shouldConsumeSmsNotification() {

        NotificationMessage message = new NotificationMessage();
        message.setNotificationId(1L);
        message.setRecipient("9986492003");
        message.setSubject("");
        message.setMessage("Test SMS");

        smsConsumer.consumeSms(message);

        verify(smsNotificationService).send(message);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.SENT);
    }

    @Test
    void shouldHandleSmsFailure() {

        NotificationMessage message = new NotificationMessage();
        message.setNotificationId(1L);
        message.setRecipient("9986492003");
        message.setMessage("Test SMS");

        doThrow(new RuntimeException("SMS Gateway Down"))
                .when(smsNotificationService)
                .send(message);

        assertThrows(RuntimeException.class, () ->
                smsConsumer.consumeSms(message));

        verify(smsNotificationService).send(message);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.FAILED);
    }
}
