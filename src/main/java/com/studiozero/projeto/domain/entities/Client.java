
package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.ClientType;
import java.time.LocalDate;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String contact;
    private ClientType clientType;
    private Boolean active;
    private LocalDate birthDay;
    private LocalDate createdDate;

    public Client(UUID id, String name, String cpf, String email, String contact, ClientType clientType, Boolean active,
            LocalDate birthDay, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.contact = contact;
        this.clientType = clientType;
        this.active = active;
        this.birthDay = birthDay;
        this.createdDate = createdDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}