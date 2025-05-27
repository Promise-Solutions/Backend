package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.exceptions.BadRequestException;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final CommandRepository commandRepository;
    private final TracingService tracingService;

    public Client createClient(Client client) {
        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new ConflictException("Client with this CPF already exists");
        }
        client.setId(UUID.randomUUID());
        client.setCreatedDate(LocalDate.now());
        tracingService.setTracing(Context.USER);
        return clientRepository.save(client);
    }

    public Client findClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(Client client) {
        if (clientRepository.existsById(client.getId())) {
            client.setId(client.getId());
            tracingService.setTracing(Context.USER);
            return clientRepository.save(client);
        }
        throw new NotFoundException("Client not found");
    }

    public void deleteClient(UUID id) {
        if (commandRepository.existsByClient_Id(id)) {
            throw new BadRequestException("Cannot delete client with associated commands");
        }

        if (clientRepository.existsById(id)) {
            tracingService.setTracing(Context.USER);
            clientRepository.deleteById(id);
        } else {
            throw new NotFoundException("Client not found");
        }
    }
}