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
    private String password;
    private String token;
    private Boolean active;

    public EmployeeResponseDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.contact = employee.getContact();
        this.cpf = employee.getCpf();
        this.password = employee.getPassword();
        this.token = employee.getToken();
        this.active = employee.getActive();
    }
}
