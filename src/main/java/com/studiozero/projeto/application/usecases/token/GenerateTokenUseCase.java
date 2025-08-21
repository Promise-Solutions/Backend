package com.studiozero.projeto.application.usecases.token;

import com.studiozero.projeto.domain.entities.Employee;

public class GenerateTokenUseCase {
    private final String secret;

    public GenerateTokenUseCase(String secret) {
        this.secret = secret;
    }

    public String execute(Employee employee) {
        // Implemente a lógica de geração de token conforme a infraestrutura desejada
        throw new UnsupportedOperationException("Implementar geração de token na infraestrutura");
    }
}
