package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setContact(dto.getContact());
        client.setClientType(dto.getClientType());
        client.setActive(dto.getActive());
        return client;
    }

    public ClientResponseDTO toDTO(Client client) {
        return new ClientResponseDTO(client);
    }
}
