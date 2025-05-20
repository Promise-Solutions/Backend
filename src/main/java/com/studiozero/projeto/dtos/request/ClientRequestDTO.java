package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.enums.ClientType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ClientRequestDTO {

    @NotBlank(message = "Name value is mandatory")
    private String name;

    @NotBlank(message = "Cpf value is mandatory")
    @CPF
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    @NotBlank(message = "Email value is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Contact value is mandatory")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX")
    private String contact;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Client Type value is mandatory")
    private ClientType clientType;

    @NotNull(message = "Active value is mandatory")
    private Boolean active = true;

    @NotNull(message = "BirthDay value is mandatory")
    @Past
    private LocalDate birthDay;

}