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
            throw new IllegalStateException("Funcionário com este CPF já existe");
        }

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalStateException("Funcionário com este e-mail já existe");
        }

        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID());
        }

        employeeRepository.save(employee);
        return employee;
    }
}
