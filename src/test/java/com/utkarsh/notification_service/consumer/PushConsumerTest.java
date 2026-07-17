package com.utkarsh.notification_service.consumer;

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

        NotificationRequest request = new NotificationRequest();
        request.setNotificationId(1L);
        request.setRecipient("user123");
        request.setSubject("");
        request.setMessage("Test Push");

        pushConsumer.consumePush(request);

        verify(pushNotificationService).send(request);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.SENT);
    }

    @Test
    void shouldHandlePushFailure() {

        NotificationRequest request = new NotificationRequest();
        request.setNotificationId(1L);
        request.setRecipient("user123");
        request.setMessage("Test Push");

        doThrow(new RuntimeException("Push Server Down"))
                .when(pushNotificationService)
                .send(request);

        assertThrows(RuntimeException.class, () ->
                pushConsumer.consumePush(request));

        verify(pushNotificationService).send(request);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.FAILED);
    }
}
