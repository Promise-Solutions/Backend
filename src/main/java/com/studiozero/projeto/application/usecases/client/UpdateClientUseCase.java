package com.studiozero.projeto.application.usecases.client;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;

public class UpdateClientUseCase {
    private final ClientRepository clientRepository;

    public UpdateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Client client) {
        if (client == null || client.getId() == null || client.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Cliente inválido");
        }
        if (clientRepository.findById(client.getId()) == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clientRepository.save(client);
        return client;
    }
}
