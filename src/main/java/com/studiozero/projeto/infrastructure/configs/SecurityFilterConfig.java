package com.studiozero.projeto.infrastructure.configs;


import com.studiozero.projeto.domain.entities.EmployeeUserDetails;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.application.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilterConfig extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final EmployeeRepository employeeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var subject = tokenService.validateToken(token);
            var optionalEmployee = employeeRepository.findByEmail(subject);

            if (optionalEmployee.isPresent()) {
                UserDetails employee = new EmployeeUserDetails(optionalEmployee.get());

                var authentication = new UsernamePasswordAuthenticationToken(
                        employee,
                        null,
                        employee.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Employee not found with email of token: " + subject);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;

        return authHeader.replace("Bearer " , "");
    }
}
