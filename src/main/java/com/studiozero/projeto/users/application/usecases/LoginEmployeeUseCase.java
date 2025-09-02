package com.studiozero.projeto.users.application.usecases;

import com.studiozero.projeto.users.domain.entities.Employee;
import com.studiozero.projeto.users.domain.repositories.EmployeeRepository;

public class LoginEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public LoginEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee execute(String email, String password) {
        Employee employee = employeeRepository.findByEmailAndPassword(email, password);
        if (employee == null) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        return employee;
    }
}
