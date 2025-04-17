package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.enums.ClientType;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ClientResponseDTO {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String contact;
    private ClientType clientType;
    private Boolean active;

    public ClientResponseDTO(Client client) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.contact = contact;
        this.clientType = clientType;
        this.active = active;
    }
}
