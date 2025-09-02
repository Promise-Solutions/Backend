package com.studiozero.projeto.customers.application.client;

import com.studiozero.projeto.customers.domain.Client;
import com.studiozero.projeto.customers.domain.ClientRepository;

import java.util.UUID;

public class CreateClientUseCase {
    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Cliente inválido");
        }

        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new IllegalStateException("Cliente com este CPF já existe");
        }

        if (client.getId() == null) {
            client.setId(UUID.randomUUID());
        }

        clientRepository.save(client);
        return client;
    }
}
