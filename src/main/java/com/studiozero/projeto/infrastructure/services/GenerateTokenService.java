package com.studiozero.projeto.infrastructure.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.studiozero.projeto.domain.entities.Employee;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class GenerateTokenService {
    private final String secret;
    private final Long expiration;


    public GenerateTokenService(@Value("${JWT_SECRET}") String secret,
                                @Value("${EXPIRATION_TIME}") Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String execute(Employee employee) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("${JWT_SECRET}")
                    .withSubject(employee.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
