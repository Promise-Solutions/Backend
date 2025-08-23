package com.studiozero.projeto.application.usecases.client;

import java.util.UUID;

import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.domain.repositories.CommandRepository;

public class DeleteClientUseCase {
    private final ClientRepository clientRepository;
    private final CommandRepository commandRepository;

    public DeleteClientUseCase(ClientRepository clientRepository, CommandRepository commandRepository) {
        this.clientRepository = clientRepository;
        this.commandRepository = commandRepository;
    }

    public void execute(UUID id) {
        if (commandRepository.existsByClientId((id))) {
            throw new IllegalStateException("Não é possível deletar cliente com comandos associados");
        }
        if (clientRepository.findById(id) == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clientRepository.deleteById(id);
    }
}
