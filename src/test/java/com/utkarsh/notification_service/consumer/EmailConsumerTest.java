package com.utkarsh.notification_service.consumer;

import com.utkarsh.notification_service.dto.NotificationMessage;
import com.utkarsh.notification_service.dto.NotificationRequest;
import com.utkarsh.notification_service.enums.NotificationStatus;
import com.utkarsh.notification_service.service.EmailNotificationService;
import com.utkarsh.notification_service.service.NotificationPersistenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
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

        NotificationMessage message = new NotificationMessage();
        message.setNotificationId(1L);
        message.setRecipient("utkarsh@gmail.com");
        message.setSubject("Test Subject");
        message.setMessage("Test Email");

        emailConsumer.consumeEmail(message);

        verify(emailNotificationService).send(message);
        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.SENT);
    }

    @Test
    void shouldHandleEmailFailure() {

        NotificationMessage message = new NotificationMessage();
        message.setNotificationId(1L);
        message.setRecipient("utkarsh@gmail.com");
        message.setSubject("Test Subject");
        message.setMessage("Test Email");

        doThrow(new RuntimeException("SMTP Down"))
                .when(emailNotificationService)
                .send(message);

        assertThrows(RuntimeException.class, () ->
                emailConsumer.consumeEmail(message));

        verify(emailNotificationService).send(message);

        verify(persistenceService)
                .updateStatus(1L, NotificationStatus.FAILED);
    }
}
