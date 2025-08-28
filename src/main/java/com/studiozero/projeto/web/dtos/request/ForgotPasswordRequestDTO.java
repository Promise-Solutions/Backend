package com.studiozero.projeto.web.dtos.request;

import jakarta.validation.constraints.Email;

public class ForgotPasswordRequestDTO {

    @Email
    private String Email;

    public ForgotPasswordRequestDTO(String email) {
        Email = email;
    }

    public ForgotPasswordRequestDTO() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
