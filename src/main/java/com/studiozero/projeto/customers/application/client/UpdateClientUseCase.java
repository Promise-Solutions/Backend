package com.studiozero.projeto.customers.application.client;

import com.studiozero.projeto.customers.domain.Client;
import com.studiozero.projeto.customers.domain.ClientRepository;

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
