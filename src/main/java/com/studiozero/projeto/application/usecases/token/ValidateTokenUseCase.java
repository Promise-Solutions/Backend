package com.studiozero.projeto.application.usecases.token;

public class ValidateTokenUseCase {
    private final String secret;

    public ValidateTokenUseCase(String secret) {
        this.secret = secret;
    }

    public String execute(String token) {
        // Implemente a lógica de validação de token conforme a infraestrutura desejada
        throw new UnsupportedOperationException("Implementar validação de token na infraestrutura");
    }
}
