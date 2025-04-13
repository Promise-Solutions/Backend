package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.exceptions.BadRequestException;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.ClientMapper;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private CommandRepository commandRepository;

    public ClientResponseDTO save(ClientRequestDTO clientDto) {
        if (clientRepository.existsByCpf(clientDto.getCpf())) {
            throw new ConflictException("Client with this CPF already exists");
        }
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDTO(savedClient);
    }

    public ClientResponseDTO findById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));
        return clientMapper.toDTO(client);
    }

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    public ClientResponseDTO update(UUID id, ClientRequestDTO clientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        if (!client.getCpf().equals(clientDto.getCpf()) && clientRepository.existsByCpf(clientDto.getCpf())) {
            throw new ConflictException("Client with this CPF already exists");
        }

        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setEmail(clientDto.getEmail());
        client.setContact(clientDto.getContact());
        client.setClientType(clientDto.getClientType());
        client.setActive(clientDto.getActive());

        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDTO(updatedClient);
    }

    public void delete(UUID id) {
        if (commandRepository.existsByFkClient(id)) {
            throw new BadRequestException("Cannot delete client with associated commands");
        }
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));
        clientRepository.delete(client);
    }
}