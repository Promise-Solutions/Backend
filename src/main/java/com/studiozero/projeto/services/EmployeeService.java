package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.exceptions.UnauthorizedException;
import com.studiozero.projeto.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public Employee login(String email, String password) {
        Optional<Employee> optEmployee = employeeRepository.findByEmailAndPassword(email,password);

        if (optEmployee.isEmpty()) {
            throw new UnauthorizedException("Credentials not found");
        }
        return optEmployee.get();
    }

    public Employee findEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            employee.setId(employee.getId());
            return employeeRepository.save(employee);
        }

        throw new NotFoundException("Employee not found");
    }

    public void deleteEmployee(UUID id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }
}
