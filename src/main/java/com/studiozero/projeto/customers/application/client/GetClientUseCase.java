package com.studiozero.projeto.customers.application.client;

import java.util.UUID;

import com.studiozero.projeto.customers.domain.Client;
import com.studiozero.projeto.customers.domain.ClientRepository;

public class GetClientUseCase {
    private final ClientRepository clientRepository;

    public GetClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(UUID id) {
        Client client = clientRepository.findById(id);
        if (client == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return client;
    }
}