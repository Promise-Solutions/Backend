package com.studiozero.projeto.infrastructure.services;

import com.studiozero.projeto.domain.repositories.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

@Service
public class ScheduledEmailTokenResetPassword {
    private static final Logger log = LoggerFactory.getLogger(ScheduledEmailTask.class);

    private final EmailRepository emailRepository;

    public ScheduledEmailTokenResetPassword(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void execute(String email, String token) {

        if (email == null) {
            log.warn("⚠️ No valid email found. Notification will not be sent.");
            return;
        }

        StringBuilder content = new StringBuilder();
        content.append("Olá, aqui está seu código para redefinir sua senha!.\n\n");
        content.append("Código: " + token);
        String subject = "StudioZero: Código para redefinição de senha!";

        try {
            emailRepository.sendTokenEmail(email, subject, content.toString());
            log.info("📧 Notification emails sent successfully to: {}", email);
        } catch (MailException e) {
            log.error("❌ Error trying to send emails to employees. Cause: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("❌ Unexpected error while executing scheduled email task: {}", e.getMessage(), e);
        }
    }
}
