package com.studiozero.projeto.infrastructure.configs;

import com.studiozero.projeto.domain.entities.EmployeeUserDetails;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.infrastructure.services.ValidateTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class SecurityFilterConfig extends OncePerRequestFilter {

    private final ValidateTokenService validateTokenService;
    private final EmployeeRepository employeeRepository;

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-resources",
            "/webjars",
            "/h2-console",
            "/actuator",
            // Note: do NOT include "/api/employees/" here because it will skip the JWT filter
            // for endpoints like /api/employees/{id}. Keep only truly public auth endpoints below.
            "/api/auth/forgot-password",
            "/api/auth/reset-password",
            "/api/error"
    );

    // Make these dependencies optional so @WebMvcTest and other slice tests can start the context
    public SecurityFilterConfig(@Autowired(required = false) ValidateTokenService validateTokenService,
                                @Autowired(required = false) EmployeeRepository employeeRepository) {
        this.validateTokenService = validateTokenService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        // If either dependency is missing (e.g. in slice tests), skip token validation gracefully
        if (token != null && validateTokenService != null && employeeRepository != null) {
            try {
                var subject = validateTokenService.execute(token);
                var employee = employeeRepository.findByEmail(subject);

                if (employee != null) {
                    UserDetails userDetails = new EmployeeUserDetails(employee);
                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                System.err.println("Erro ao validar token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;

        // Normalize and validate header
        authHeader = authHeader.trim();
        if (authHeader.length() < 7) return null; // too short to contain 'Bearer '
        if (!authHeader.substring(0, 6).equalsIgnoreCase("Bearer")) return null;
        return authHeader.substring(6).trim();
    }
}