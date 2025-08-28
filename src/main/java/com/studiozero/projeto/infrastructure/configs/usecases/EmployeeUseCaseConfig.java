package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.employee.*;
import com.studiozero.projeto.infrastructure.repositories.Implements.EmployeeRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeUseCaseConfig {
    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new CreateEmployeeUseCase(employeeRepository);
    }

    @Bean
    public DeleteEmployeeUseCase deleteEmployeeUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new DeleteEmployeeUseCase(employeeRepository);
    }

    @Bean
    public DeleteEmployeeWithUserUseCase deleteEmployeeWithUserUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new DeleteEmployeeWithUserUseCase(employeeRepository);
    }

    @Bean
    public GetEmployeeUseCase getEmployeeUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new GetEmployeeUseCase(employeeRepository);
    }

    @Bean
    public ListEmployeesUseCase listEmployeesUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new ListEmployeesUseCase(employeeRepository);
    }

    @Bean
    public UpdateEmployeeUseCase updateEmployeeUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new UpdateEmployeeUseCase(employeeRepository);
    }
}

