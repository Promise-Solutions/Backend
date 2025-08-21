package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.repositories.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaEmailRepository implements EmailRepository {
    private final JavaMailSender mailSender;

    @Override
    public void enviarAviso(List<String> destinatarios, String assunto, String conteudo) {
        for (String destinatario : destinatarios) {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(destinatario);
            mensagem.setSubject(assunto);
            mensagem.setText(conteudo);
            mailSender.send(mensagem);
        }
    }
}
