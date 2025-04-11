package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.exceptions.EntityNotFoundException;
import com.studiozero.projeto.mappers.ClientMapper;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public ClientResponseDTO save(ClientRequestDTO clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);

        return clientMapper.toDTO(savedClient);
    }

    public ClientResponseDTO findById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        return clientMapper.toDTO(client);
    }

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    public ClientResponseDTO update(UUID id, ClientRequestDTO clientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setEmail(clientDto.getEmail());
        client.setContact(clientDto.getContact());
        client.setClientType(clientDto.getClientType());
        client.setActive(clientDto.getActive());

        Client updatedClient = clientRepository.save(client);

        return clientMapper.toDTO(updatedClient);
    }

    public String delete(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        clientRepository.delete(client);
        return "Client deleted successfully";
    }
}