package com.studiozero.projeto.customers.application.client;

import com.studiozero.projeto.customers.domain.Client;
import com.studiozero.projeto.customers.domain.ClientRepository;
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
