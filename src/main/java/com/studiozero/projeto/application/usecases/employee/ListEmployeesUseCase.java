package com.studiozero.projeto.application.usecases.employee;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import java.util.List;

public class ListEmployeesUseCase {
    private final EmployeeRepository employeeRepository;

    public ListEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> execute() {
        return employeeRepository.listAll();
    }
}
