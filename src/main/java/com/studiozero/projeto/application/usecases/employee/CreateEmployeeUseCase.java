package com.studiozero.projeto.application.usecases.employee;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import java.util.UUID;

public class CreateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public CreateEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee execute(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Funcionário inválido");
        }

        if (employeeRepository.existsByCpf(employee.getCpf())) {
            throw new IllegalStateException("Funcionário já existe com este CPF");
        }

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalStateException("Funcionário já existe com este e-mail ");
        }

        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID());
        }

        employeeRepository.save(employee);
        return employee;
    }
}
