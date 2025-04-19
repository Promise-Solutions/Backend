package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Employee;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class EmployeeResponseDTO {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String contact;
    private Boolean active;

    public EmployeeResponseDTO(Employee employee) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.contact = contact;
        this.active = active;
    }
}
