package com.studiozero.projeto.domain.repositories;

import java.util.List;

public interface EmailRepository {
    void sendEmail(List<String> destinatarios, String assunto, String conteudo);

    void sendTokenEmail(String destinatario, String assunto, String conteudo);
}
