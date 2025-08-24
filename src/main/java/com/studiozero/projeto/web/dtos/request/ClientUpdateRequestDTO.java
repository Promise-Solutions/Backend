package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.ClientType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

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

    public ClientUpdateRequestDTO() {
    }

    public ClientUpdateRequestDTO(String name, String cpf, String email, String contact, ClientType clientType, Boolean active, LocalDate createdDate, LocalDate birthDay) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.contact = contact;
        this.clientType = clientType;
        this.active = active;
        this.createdDate = createdDate;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @CPF @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00") String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00") String cpf) {
        this.cpf = cpf;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX") String getContact() {
        return contact;
    }

    public void setContact(@Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX") String contact) {
        this.contact = contact;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public @NotNull(message = "BirthDay value is mandatory") @Past LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(@NotNull(message = "BirthDay value is mandatory") @Past LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
