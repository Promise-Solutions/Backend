package com.studiozero.projeto.users.application.usecases;

import java.util.UUID;

import com.studiozero.projeto.users.domain.entities.Employee;
import com.studiozero.projeto.users.domain.repositories.EmployeeRepository;

public class GetEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public GetEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee execute(UUID id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        return employee;
    }
}