package com.studiozero.projeto.application.usecases.employee;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;

public class UpdateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee execute(Employee employee) {
        if (employee == null || employee.getId() == null || employee.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Funcionário inválido");
        }
        Employee existing = employeeRepository.findById(employee.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        employeeRepository.save(employee);
        return employee;
    }
}
