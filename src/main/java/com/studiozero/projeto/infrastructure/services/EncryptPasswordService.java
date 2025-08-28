package com.studiozero.projeto.infrastructure.services;

import com.studiozero.projeto.domain.entities.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptPasswordService {
    private final PasswordEncoder passwordEncoder;

    public EncryptPasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Employee encryptPassword(Employee employee){
        if (employee == null){
            throw new RuntimeException();
        }

        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employee;
    }
}
