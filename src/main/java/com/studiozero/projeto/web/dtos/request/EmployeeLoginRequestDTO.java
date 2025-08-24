package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class EmployeeLoginRequestDTO {

    @NotBlank(message = "Email value is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password value is mandatory")
    private String password;

    public EmployeeLoginRequestDTO() {
    }

    public EmployeeLoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public @NotBlank(message = "Email value is mandatory") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email value is mandatory") @Email String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password value is mandatory") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password value is mandatory") String password) {
        this.password = password;
    }
}
