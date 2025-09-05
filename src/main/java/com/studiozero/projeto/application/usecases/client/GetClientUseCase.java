package com.studiozero.projeto.application.usecases.client;

import java.util.UUID;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;

public class GetClientUseCase {
    private final ClientRepository clientRepository;

    public GetClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(UUID id) {
        Client client = null;
        if (id != null) {
            client = clientRepository.findById(id);
        }

        return client;
    }
}