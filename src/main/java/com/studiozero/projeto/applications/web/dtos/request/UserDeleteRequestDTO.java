package com.studiozero.projeto.applications.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class UserDeleteRequestDTO {
    @NotNull
    private UUID id;

    public @NotNull UUID getId() {
        return id;
    }

    public void setId(@NotNull UUID id) {
        this.id = id;
    }
}
