package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.EmailNotificationService;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailConsumerTest {

    @Mock
    private EmailNotificationService emailNotificationService;

    @Mock
    private NotificationPersistenceService persistenceService;

    @InjectMocks
    private EmailConsumer emailConsumer;

    @Test
    void shouldConsumeEmailNotification() {

        NotificationRequest request = new NotificationRequest();
        request.setNotificationId(1L);
        request.setRecipient("utkarsh@gmail.com");
        request.setSubject("Test Subject");
        request.setMessage("Test Email");

        emailConsumer.consumeEmail(request);

        verify(emailNotificationService).send(request);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.SENT);
    }
}
