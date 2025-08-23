package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.EmployeeRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaEmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.application.usecases.employee.CreateEmployeeUseCase;
import com.studiozero.projeto.application.usecases.employee.DeleteEmployeeUseCase;
import com.studiozero.projeto.application.usecases.employee.DeleteEmployeeWithUserUseCase;
import com.studiozero.projeto.application.usecases.employee.GetEmployeeUseCase;
import com.studiozero.projeto.application.usecases.employee.ListEmployeesUseCase;
import com.studiozero.projeto.application.usecases.employee.UpdateEmployeeUseCase;

@Configuration
public class EmployeeUseCaseConfig {
    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(EmployeeRepository employeeRepository) {
        return new CreateEmployeeUseCase(employeeRepository);
    }

    @Bean
    public DeleteEmployeeUseCase deleteEmployeeUseCase(EmployeeRepository employeeRepository) {
        return new DeleteEmployeeUseCase(employeeRepository);
    }

    @Bean
    public DeleteEmployeeWithUserUseCase deleteEmployeeWithUserUseCase(EmployeeRepository employeeRepository) {
        return new DeleteEmployeeWithUserUseCase(employeeRepository);
    }

    @Bean
    public GetEmployeeUseCase getEmployeeUseCase(EmployeeRepository employeeRepository) {
        return new GetEmployeeUseCase(employeeRepository);
    }

    @Bean
    public ListEmployeesUseCase listEmployeesUseCase(EmployeeRepository employeeRepository) {
        return new ListEmployeesUseCase(employeeRepository);
    }

    @Bean
    public UpdateEmployeeUseCase updateEmployeeUseCase(EmployeeRepository employeeRepository) {
        return new UpdateEmployeeUseCase(employeeRepository);
    }

    @Bean
    public EmployeeRepositoryImpl employeeRepositoryImpl(JpaEmployeeRepository jpaEmployeeRepository) {
        return new EmployeeRepositoryImpl(jpaEmployeeRepository);
    }


}

