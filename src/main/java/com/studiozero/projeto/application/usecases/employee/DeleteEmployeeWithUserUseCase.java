package com.studiozero.projeto.application.usecases.employee;

import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import java.util.UUID;

public class DeleteEmployeeWithUserUseCase {
    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeWithUserUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void execute(UUID id, UUID userLogged) {
        // Aqui pode-se adicionar lógica de autorização/auditoria se necessário
        employeeRepository.deleteById(id);
    }
}
