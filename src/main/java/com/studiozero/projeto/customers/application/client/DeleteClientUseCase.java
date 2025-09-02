package com.studiozero.projeto.customers.application.client;

import java.util.UUID;

import com.studiozero.projeto.customers.domain.ClientRepository;
import com.studiozero.projeto.sales.domain.repository.CommandRepository;

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
