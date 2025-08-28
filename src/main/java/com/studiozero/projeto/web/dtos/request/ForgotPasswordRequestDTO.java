package com.studiozero.projeto.web.dtos.request;

import jakarta.validation.constraints.Email;

public class ForgotPasswordRequestDTO {

    @Email
    String email;

    public ForgotPasswordRequestDTO(String email) {
        this.email = email;
    }

    public ForgotPasswordRequestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
