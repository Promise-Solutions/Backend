package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.infrastructure.entities.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientEntityMapper {

    public static ClientEntity toEntity(Client client) {
        if (client == null)
            return null;
        return new ClientEntity(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getEmail(),
                client.getContact(),
                client.getClientType(),
                client.getActive(),
                client.getBirthDay(),
                java.time.LocalDate.now()
        );
    }


    public static Client toDomain(ClientEntity clientEntity) {
        if (clientEntity == null)
            return null;
        return new Client(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getCpf(),
                clientEntity.getEmail(),
                clientEntity.getContact(),
                clientEntity.getClientType(),
                clientEntity.getActive(),
                clientEntity.getBirthDay(),
                clientEntity.getCreatedDate()
        );
    }

    public static List<Client> toDomainList(List<ClientEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(ClientEntityMapper::toDomain).toList();
    }

    public static List<ClientEntity> toEntityList(List<Client> clients) {
        if (clients == null) return null;
        return clients.stream().map(ClientEntityMapper::toEntity).toList();
    }
}
