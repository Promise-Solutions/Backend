package com.studiozero.projeto.application.usecases.client;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import java.util.List;

public class ListClientsUseCase {
    private final ClientRepository clientRepository;

    public ListClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> execute() {
        return clientRepository.findAll();
    }
}
