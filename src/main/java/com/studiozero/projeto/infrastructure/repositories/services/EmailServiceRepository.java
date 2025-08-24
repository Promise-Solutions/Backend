package com.studiozero.projeto.infrastructure.repositories.services;

import com.studiozero.projeto.domain.repositories.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmailServiceRepository implements EmailRepository {
    private final JavaMailSender mailSender;

    public EmailServiceRepository(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(List<String> destinatarios, String assunto, String conteudo) {
        for (String destinatario : destinatarios) {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(destinatario);
            mensagem.setSubject(assunto);
            mensagem.setText(conteudo);
            mailSender.send(mensagem);
        }
    }

}
