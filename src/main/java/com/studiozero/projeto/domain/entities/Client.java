
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

        validateName(name);
        validateEmail(email);
        validateContact(contact);
        validateCpf(cpf);
        if (active == null) {
            throw new IllegalArgumentException("Active cannot be null");
        }

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

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (name.length() < 2 || name.length() > 100) {
            throw new IllegalArgumentException("Name must be between 2 and 100 characters");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.contains("@") || email.length() < 5 || email.length() > 100) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void validateContact(String contact) {
        if (contact == null || contact.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact cannot be null or empty");
        }
        if (contact.length() < 8 || contact.length() > 20) {
            throw new IllegalArgumentException("Contact must be between 8 and 20 characters");
        }
    }

    private void validateCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF cannot be null or empty");
        }
        if (cpf.length() != 14) {
            throw new IllegalArgumentException("CPF must be 14 digits");
        }
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF must contain only digits");
        }
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