package com.studiozero.projeto.application.usecases.employee;

import java.util.UUID;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;

public class GetEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public GetEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee execute(UUID id) {
        Employee employee = null;

        if (id != null) {
            employee = employeeRepository.findById(id);
        }

        return employee;
    }
}