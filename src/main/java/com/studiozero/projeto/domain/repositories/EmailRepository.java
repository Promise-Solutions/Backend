package com.studiozero.projeto.domain.repositories;

import java.util.List;

public interface EmailRepository {
    void enviarAviso(List<String> destinatarios, String assunto, String conteudo);
}
