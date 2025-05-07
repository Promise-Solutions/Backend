package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByCpf(employee.getCpf())) {
            throw new ConflictException("Employee with this CPF already exists");
        }

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ConflictException("Employee with this email already exists");
        }

        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);
        employee.setId(UUID.randomUUID());
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.findById(employee.getId())
                .map(existingEmployee -> {
                    if (employee.getName() != null) {
                        existingEmployee.setName(employee.getName());
                    }
                    if (employee.getEmail() != null) {
                        existingEmployee.setEmail(employee.getEmail());
                    }
                    if (employee.getContact() != null) {
                        existingEmployee.setContact(employee.getContact());
                    }
                    if (employee.getCpf() != null) {
                        existingEmployee.setCpf(employee.getCpf());
                    }
                    if (employee.getPassword() != null) {
                        existingEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
                    }
                    if (employee.getActive() != null) {
                        existingEmployee.setActive(employee.getActive());
                    }

                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public void deleteEmployee(UUID id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }
}
