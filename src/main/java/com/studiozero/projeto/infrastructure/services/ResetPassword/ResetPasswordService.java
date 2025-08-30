package com.studiozero.projeto.infrastructure.services.ResetPassword;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {
    private final EmployeeRepository employeeRepository;
    private final TokenResetPasswordService tokenService;
    private final ScheduledEmailTokenResetPassword emailService;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(EmployeeRepository userRepository, TokenResetPasswordService tokenService, ScheduledEmailTokenResetPassword emailService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = userRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void requestReset(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) {
            throw new IllegalArgumentException("Funcionário não encontrado para o email");
        }
        String token = tokenService.generateResetToken(employee);
        emailService.execute(employee.getEmail(), token);
    }

    public void resetPassword(String token, String newPassword) {
        String email = tokenService.validateResetToken(token);

        Employee employee = employeeRepository.findByEmail(email);

        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepository.save(employee);
    }
}
