package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.service.PushNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PushConsumerTest {

    @Mock
    private PushNotificationService pushNotificationService;

    @InjectMocks
    private PushConsumer pushConsumer;

    @Test
    void shouldConsumePushNotification() {

        NotificationRequest request = new NotificationRequest();
        request.setRecipient("user123");
        request.setSubject("");
        request.setMessage("Test Push");

        pushConsumer.consumePush(request);

        verify(pushNotificationService).send(request);
    }
}
