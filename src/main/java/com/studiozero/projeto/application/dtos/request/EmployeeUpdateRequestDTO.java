package com.studiozero.projeto.application.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class EmployeeUpdateRequestDTO {
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX")
    private String contact;

    @CPF
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    private String password;

    private Boolean active = true;

    public EmployeeUpdateRequestDTO(Employee employee) {
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.contact = employee.getContact();
        this.cpf = employee.getCpf();
        this.password = employee.getPassword();
        this.active = employee.getActive();
    }
}
