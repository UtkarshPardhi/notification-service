package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import com.utkarsh.notification_service.service.PushNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PushConsumerTest {

    @Mock
    private PushNotificationService pushNotificationService;

    @Mock
    private NotificationPersistenceService persistenceService;

    @InjectMocks
    private PushConsumer pushConsumer;

    @Test
    void shouldConsumePushNotification() {

        NotificationMessage message = new NotificationMessage();
        message.setNotificationId(1L);
        message.setRecipient("user123");
        message.setSubject("");
        message.setMessage("Test Push");

        pushConsumer.consumePush(message);

        verify(pushNotificationService).send(message);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.SENT);
    }

    @Test
    void shouldHandlePushFailure() {

        NotificationMessage message = new NotificationMessage();
        message.setNotificationId(1L);
        message.setRecipient("user123");
        message.setMessage("Test Push");

        doThrow(new RuntimeException("Push Server Down"))
                .when(pushNotificationService)
                .send(message);

        assertThrows(RuntimeException.class, () ->
                pushConsumer.consumePush(message));

        verify(pushNotificationService).send(message);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.FAILED);
    }
}
