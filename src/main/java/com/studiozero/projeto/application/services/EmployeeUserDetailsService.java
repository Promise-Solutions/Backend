package com.studiozero.projeto.application.services;

import com.studiozero.projeto.domain.entities.EmployeeUserDetails;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeRepository.findByEmail(email)
                .map(EmployeeUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + email));
    }
}
