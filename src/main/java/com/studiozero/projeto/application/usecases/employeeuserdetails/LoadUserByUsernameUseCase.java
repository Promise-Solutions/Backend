package com.studiozero.projeto.application.usecases.employeeuserdetails;

public class LoadUserByUsernameUseCase {
    public Object execute(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        throw new UnsupportedOperationException("Implementar loadUserByUsername na infraestrutura");
    }
}
