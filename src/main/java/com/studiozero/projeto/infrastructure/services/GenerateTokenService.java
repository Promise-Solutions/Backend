package com.studiozero.projeto.infrastructure.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.studiozero.projeto.domain.entities.Employee;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class GenerateTokenService {
    private final String SECRET;
    private final Long EXPIRATION;
    private static final String ISSUER = "studiozero-auth";

    public GenerateTokenService(String SECRET, Long EXPIRATION) {
        this.SECRET = SECRET;
        this.EXPIRATION = EXPIRATION;
    }

    public String execute(Employee employee) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(employee.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(EXPIRATION).toInstant(ZoneOffset.of("-03:00"));
    }
}
