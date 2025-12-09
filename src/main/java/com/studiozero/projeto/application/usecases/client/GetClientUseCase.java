package com.studiozero.projeto.application.usecases.client;

import java.util.Objects;
import java.util.UUID;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;

public class GetClientUseCase {
    private final ClientRepository clientRepository;

    public GetClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(UUID id) {
        Objects.requireNonNull(id, "id must not be null");

        Client clientFound = clientRepository.findById(id);

        if(clientFound == null) {
            throw new IllegalArgumentException("client not found for id: "+ id);
        }

        return clientFound;
    }
}