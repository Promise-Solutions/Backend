package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.studiozero.projeto.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class TokenServiceTest {

    private TokenService tokenService;
    private final String secret = "my-test-secret";

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", secret);
    }

    @Test
    @DisplayName("Should generate a valid token for a given employee")
    void shouldGenerateValidToken() {
        Employee employee = new Employee();
        employee.setEmail("user@example.com");

        String token = tokenService.generateToken(employee);

        assertNotNull(token);
        assertFalse(token.isBlank());
        assertTrue(token.split("\\.").length == 3); // JWT has 3 parts
    }

    @Test
    @DisplayName("Should validate a valid token and return the subject (email)")
    void shouldValidateTokenSuccessfully() {
        String email = "user@example.com";
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("studio-zero")
                .withSubject(email)
                .withExpiresAt(java.time.Instant.now().plusSeconds(3600))
                .sign(algorithm);

        String subject = tokenService.validateToken(token);

        assertEquals(email, subject);
    }

    @Test
    @DisplayName("Should return empty string when token is invalid")
    void shouldReturnEmptyStringForInvalidToken() {
        String invalidToken = "invalid.token.value";

        String result = tokenService.validateToken(invalidToken);

        assertEquals("", result);
    }
}
