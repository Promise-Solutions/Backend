package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.applications.web.dtos.request.ClientDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.exceptions.EntityNotFoundException;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.applications.web.dtos.request.ClientCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientResponseDTO save(ClientCreateRequestDTO clientDto) {

        Client client = new Client();
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setEmail(clientDto.getEmail());
        client.setContact(clientDto.getContact());
        client.setClientType(clientDto.getClientType());
        client.setActive(clientDto.getActive());

        Client savedClient = clientRepository.save(client);

        return new ClientResponseDTO(savedClient);
    }

    @Override
    public List<ClientResponseDTO> list() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientResponseDTO::new)
                .toList();
    }

    @Override
    public ClientResponseDTO update(ClientUpdateRequestDTO clientDto) {
        Client client = clientRepository.findById(clientDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setEmail(clientDto.getEmail());
        client.setContact(clientDto.getContact());
        client.setClientType(clientDto.getClientType());
        client.setActive(clientDto.getActive());

        Client updatedClient = clientRepository.save(client);

        return new ClientResponseDTO(updatedClient);
    }

    @Override
    public String delete(ClientDeleteRequestDTO clientDto) {
        Client client = clientRepository.findById(clientDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        clientRepository.delete(client);
        return "Client deleted successfully";
    }
}