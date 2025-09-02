package com.studiozero.projeto.users.application.usecases;

import com.studiozero.projeto.users.domain.entities.Employee;
import com.studiozero.projeto.users.domain.repositories.EmployeeRepository;
import java.util.List;

public class ListEmployeesUseCase {
    private final EmployeeRepository employeeRepository;

    public ListEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> execute() {
        return employeeRepository.findAll();
    }
}
