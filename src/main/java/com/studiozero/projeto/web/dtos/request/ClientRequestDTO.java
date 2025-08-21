package com.studiozero.projeto.web.dtos.request;

import com.studiozero.projeto.application.enums.ClientType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;

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
    @Pattern(regexp="\\(\\d{2}\\) \\d{5}-\\d{4}",message="Contact must be in the format (XX) XXXXX-XXXX")
    private String contact;

    @NotNull(message = "Client Type value is mandatory")
    private ClientType clientType;

    @NotNull(message = "Active value is mandatory")
    private Boolean active = true;

    @NotNull(message = "BirthDay value is mandatory")
    @Past
    private LocalDate birthDay;

    public ClientRequestDTO() {
    }

    public ClientRequestDTO(String name, String cpf, String email, String contact, ClientType clientType,
            Boolean active, LocalDate birthDay) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.contact = contact;
        this.clientType = clientType;
        this.active = active;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
