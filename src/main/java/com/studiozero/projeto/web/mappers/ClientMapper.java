package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.web.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.ClientResponseDTO;
import java.util.List;
import java.util.UUID;

public class ClientMapper {
    public static Client toDomain(ClientRequestDTO dto) {
        if (dto == null) return null;
        return new Client(
            null,
            dto.getName(),
            dto.getCpf(),
            dto.getEmail(),
            dto.getContact(),
            dto.getClientType(),
            dto.getActive(),
            dto.getBirthDay(),
            null
        );
    }

    public static Client toDomain(ClientUpdateRequestDTO dto, UUID id) {
        if (dto == null) return null;
        return new Client(
            id,
            dto.getName(),
            dto.getCpf(),
            dto.getEmail(),
            dto.getContact(),
            dto.getClientType(),
            dto.getActive(),
            dto.getBirthDay(),
            dto.getCreatedDate()
        );
    }

    public static ClientResponseDTO toDTO(Client client) {
        if (client == null) return null;
        return new ClientResponseDTO(
            client.getId(),
            client.getName(),
            client.getCpf(),
            client.getEmail(),
            client.getContact(),
            client.getClientType(),
            client.getActive(),
            client.getBirthDay(),
            client.getCreatedDate()
        );
    }

    public static List<ClientResponseDTO> toDTOList(List<Client> clients) {
        if (clients == null) return null;
        return clients.stream().map(ClientMapper::toDTO).toList();
    }
}
