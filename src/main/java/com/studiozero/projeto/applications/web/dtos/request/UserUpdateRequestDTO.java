package com.studiozero.projeto.applications.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class UserUpdateRequestDTO {
    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$")
    private String password;

    public @NotBlank @Size(min = 3, max = 100) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 3, max = 100) String name) {
        this.name = name;
    }

    public @NotBlank @CPF String getCpf(){
        return cpf;
    }

    public void setCpf(@NotBlank @CPF String cpf){
        this.cpf = cpf;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 8, max = 50) @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$"
    ) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 8, max = 50) @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$"
    ) String password) {
        this.password = password;
    }
}
