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

        existing.changeName(employee.getName());
        existing.changeEmail(employee.getEmail());
        existing.changeContact(employee.getContact());
        existing.changeCpf(employee.getCpf());
        existing.changeActive(employee.getActive());

        employeeRepository.save(existing);
        return existing;
    }
}
