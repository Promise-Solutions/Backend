package com.studiozero.projeto.application.usecases.employee;

import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.web.handlers.DeleteOwnUserException;

import java.util.UUID;

public class DeleteEmployeeWithUserUseCase {
    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeWithUserUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void execute(UUID id, UUID userLogged) {
        if (id.equals(userLogged)) {
            throw new DeleteOwnUserException("Você não pode excluir a si mesmo.");
        }

        employeeRepository.deleteById(id);
    }
}
