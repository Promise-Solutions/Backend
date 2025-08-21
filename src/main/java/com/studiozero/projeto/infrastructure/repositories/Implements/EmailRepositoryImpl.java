package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.repositories.EmailRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {
    @Override
    public void enviarAviso(List<String> destinatarios, String assunto, String conteudo) {
        // Implementação de envio de email real deve ser feita aqui
        // Exemplo: usar JavaMailSender ou outro serviço
        // Por enquanto, apenas simula
        System.out.printf("Enviando email para %s: %s - %s\n", destinatarios, assunto, conteudo);
    }
}
