package com.studiozero.projeto.domain.entities;

import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private String email;
    private String contact;
    private String cpf;
    private String password;
    private Boolean active;

    public Employee() {
    }

    public Employee(UUID id, String name, String email, String contact, String cpf, String password, Boolean active) {
        validateName(name);
        validateEmail(email);
        validateContact(contact);
        validateCpf(cpf);
        validatePassword(password);
        if (active == null) {
            throw new IllegalArgumentException("Active cannot be null");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.cpf = cpf;
        this.password = password;
        this.active = active;
    }
    public Employee(UUID id, String name, String email, String contact, String cpf, Boolean active) {
        validateName(name);
        validateEmail(email);
        validateContact(contact);
        validateCpf(cpf);
        if (active == null) {
            throw new IllegalArgumentException("Active cannot be null");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.cpf = cpf;
        this.active = active;
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

    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.length() < 6 || password.length() > 100) {
            throw new IllegalArgumentException("Password must be between 6 and 100 characters");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void changeName(String newName) {
        validateName(newName);
        this.name = newName;
    }

    public void changeEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail;
    }

    public void changeContact(String newContact) {
        validateContact(newContact);
        this.contact = newContact;
    }

    public void changeCpf(String newCpf) {
        validateCpf(newCpf);
        this.cpf = newCpf;
    }

    public void changePassword(String newPassword) {
        validatePassword(newPassword);
        this.password = newPassword;
    }

    public void changeActive(Boolean newActive) {
        if (newActive == null) {
            throw new IllegalArgumentException("Active cannot be null");
        }
        this.active = newActive;
    }
}
