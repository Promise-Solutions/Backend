package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Getter
@Setter
public class EmployeeRequestDTO {

    @NotBlank(message = "Name value is mandatory")
    private String name;

    @NotBlank(message = "Email value is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Contact value is mandatory")
    private String contact;

    @NotNull(message = "Cpf value is mandatory")
    @CPF
    private String cpf;

    @NotBlank(message = "Password value is mandatory")
    private String password;

//    Esse token vai ser gerado com o JWT (JAVA WEB TOKEN)
    @NotBlank(message = "Token value is mandatory")
    private String token;

    @NotNull(message = "Active value is mandatory")
    private Boolean active;
}
