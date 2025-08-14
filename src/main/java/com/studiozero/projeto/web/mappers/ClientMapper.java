package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.application.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.application.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.application.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.domain.entities.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ClientMapper {

    public static Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setContact(dto.getContact());
        client.setClientType(dto.getClientType());
        client.setActive(dto.getActive());
        client.setBirthDay(dto.getBirthDay());
        return client;
    }

    public static ClientResponseDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setCpf(client.getCpf());
        dto.setEmail(client.getEmail());
        dto.setContact(client.getContact());
        dto.setClientType(client.getClientType());
        dto.setActive(client.getActive());
        dto.setBirthDay(client.getBirthDay());
        dto.setCreatedDate(client.getCreatedDate());
        return dto;
    }

    public static List<ClientResponseDTO> toListDtos(List<Client> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(ClientMapper::toDTO)
                .toList();
    }

    public static Client toEntity(ClientUpdateRequestDTO dto, UUID id) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();

        client.setId(id);
        if (dto.getName() != null) client.setName(dto.getName());
        if (dto.getCpf() != null) client.setCpf(dto.getCpf());
        if (dto.getEmail() != null) client.setEmail(dto.getEmail());
        if (dto.getContact() != null) client.setContact(dto.getContact());
        if (dto.getClientType() != null) client.setClientType(dto.getClientType());
        if (dto.getActive() != null) client.setActive(dto.getActive());
        if (dto.getCreatedDate() != null) client.setCreatedDate(dto.getCreatedDate());
        if (dto.getBirthDay() != null) client.setBirthDay(dto.getBirthDay());


        return client;
    }

}
