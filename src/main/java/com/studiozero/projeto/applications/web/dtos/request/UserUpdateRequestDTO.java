package com.studiozero.projeto.applications.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class UserUpdateRequestDTO {
    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$")
    private String password;

    public @NotNull UUID getId() {
        return id;
    }

    public void setId(@NotNull UUID id) {
        this.id = id;
    }

    public @NotNull @Size(min = 3, max = 100) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 3, max = 100) String name) {
        this.name = name;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 8, max = 50) @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$"
    ) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 8, max = 50) @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$"
    ) String password) {
        this.password = password;
    }
}
