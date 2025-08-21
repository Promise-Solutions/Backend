package com.studiozero.projeto.application.usecases.email;

import com.studiozero.projeto.domain.repositories.EmailRepository;
import java.util.List;

public class SendEmailNoticeUseCase {
    private final EmailRepository emailRepository;

    public SendEmailNoticeUseCase(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void execute(List<String> destinatarios, String assunto, String conteudo) {
        emailRepository.enviarAviso(destinatarios, assunto, conteudo);
    }
}
