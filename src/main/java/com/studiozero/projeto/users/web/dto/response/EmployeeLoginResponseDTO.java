package com.studiozero.projeto.users.web.dto.response;

import java.util.UUID;

public class EmployeeLoginResponseDTO {
    private final String token;
    private final UUID id;

    public EmployeeLoginResponseDTO(String token, UUID id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public UUID getId() {
        return id;
    }
}
