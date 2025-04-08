package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.enums.ClientType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ClientRequestDTO {
    @NotNull(message = "Id value is mandatory")
    private UUID id;

    @NotBlank(message = "Name value is mandatory")
    private String name;

    @NotBlank(message = "Cpf value is mandatory")
    @CPF
    private String cpf;

    @NotBlank(message = "Email value is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Contact value is mandatory")
    private String contact;

    @NotNull(message = "Client Type value is mandatory")
    private ClientType clientType;

    @NotNull(message = "Active value is mandatory")
    private Boolean active;
}