package com.studiozero.projeto.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EmployeeLoginResponseDTO {
    private final String token;
    private final UUID id;
}
