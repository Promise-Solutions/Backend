package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;

import com.studiozero.projeto.application.services.EmployeeUserDetailsService;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.repositories.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeUserDetailsServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeUserDetailsService employeeUserDetailsService;

    @Test
    @DisplayName("Should return UserDetails when employee is found by email")
    void testLoadUserByUsername_Success() {
        String email = "test@studiozero.com";
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword("secret");

        when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(employee));

        UserDetails userDetails = employeeUserDetailsService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals("secret", userDetails.getPassword());
    }

    @Test
    @DisplayName("Should throw UsernameNotFoundException when employee is not found")
    void testLoadUserByUsername_NotFound() {
        String email = "nonexistent@studiozero.com";
        when(employeeRepository.findByEmail(email)).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> employeeUserDetailsService.loadUserByUsername(email)
        );

        assertEquals("Employee not found with email: " + email, exception.getMessage());
    }
}
