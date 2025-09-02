package com.studiozero.projeto.users.infrastruture.config.usecase;

import com.studiozero.projeto.identity.application.usecases.*;
import com.studiozero.projeto.novo.users.application.usecases.*;
import com.studiozero.projeto.users.application.usecases.*;
import com.studiozero.projeto.users.infrastruture.repository.implement.EmployeeRepositoryImpl;
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

    @Bean
    public LoginEmployeeUseCase loginEmployeeUseCase(EmployeeRepositoryImpl employeeRepository) {
        return new LoginEmployeeUseCase(employeeRepository);
    }
}

