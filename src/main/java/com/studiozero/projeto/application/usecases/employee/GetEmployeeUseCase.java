package com.studiozero.projeto.application.usecases.employee;

import java.util.Objects;
import java.util.UUID;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;

public class GetEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public GetEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee execute(UUID id) {
        Objects.requireNonNull(id, "id must not be null");

        Employee employee = employeeRepository.findById(id);

        if(employee == null) {
            throw new IllegalArgumentException("employee not found for id: "+ id);
        }

        return employee;
    }
}