package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.enums.ClientType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ClientUpdateRequestDTO {
    private String name;

    @CPF
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    @Email
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX")
    private String contact;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    private Boolean active = true;

    private LocalDate createdDate;

    @NotNull(message = "BirthDay value is mandatory")
    @Past
    private LocalDate birthDay;
}
