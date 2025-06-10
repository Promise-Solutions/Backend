package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.List;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private JavaMailSender mailSender;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        mailSender = mock(JavaMailSender.class);
        emailService = new EmailService(mailSender);
    }

    @Test
    @DisplayName("Should send email to each recipient")
    void testEnviarAviso_MultipleRecipients() {
        List<String> recipients = List.of("user1@example.com", "user2@example.com");
        String subject = "Test Subject";
        String content = "Test Content";

        emailService.enviarAviso(recipients, subject, content);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(2)).send(messageCaptor.capture());

        List<SimpleMailMessage> capturedMessages = messageCaptor.getAllValues();

        for (int i = 0; i < recipients.size(); i++) {
            SimpleMailMessage msg = capturedMessages.get(i);
            assertEquals(recipients.get(i), msg.getTo()[0]);
            assertEquals(subject, msg.getSubject());
            assertEquals(content, msg.getText());
        }
    }

    @Test
    @DisplayName("Should not throw exception when recipient list is empty")
    void testEnviarAviso_EmptyRecipients() {
        assertDoesNotThrow(() -> emailService.enviarAviso(List.of(), "Subject", "Content"));
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }
}
