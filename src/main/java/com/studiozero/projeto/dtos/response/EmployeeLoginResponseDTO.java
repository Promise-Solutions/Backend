package com.studiozero.projeto.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeLoginResponseDTO {
    private final String token;
}
