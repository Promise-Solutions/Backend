package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public EmployeeResponseDTO(Employee savedEmployee) {
        this.id = savedEmployee.getId();
        this.name = savedEmployee.getName();
        this.cpf = savedEmployee.getCpf();
        this.email = savedEmployee.getEmail();
        this.contact = savedEmployee.getContact();
        this.active = savedEmployee.getActive();
    }
}
