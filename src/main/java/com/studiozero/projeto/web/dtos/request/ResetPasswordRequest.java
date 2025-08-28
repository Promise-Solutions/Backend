package com.studiozero.projeto.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequest {

    @NotBlank
    private String token;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String newPassword;
}
