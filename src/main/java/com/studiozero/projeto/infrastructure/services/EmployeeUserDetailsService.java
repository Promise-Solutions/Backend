package com.studiozero.projeto.infrastructure.services;

import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.domain.entities.EmployeeUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var employee = employeeRepository.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found with email: " + username);
        }
        return new EmployeeUserDetails(employee);
    }
}

