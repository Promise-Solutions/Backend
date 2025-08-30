package com.studiozero.projeto.infrastructure.services.ResetPassword;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.studiozero.projeto.domain.entities.Employee;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenResetPasswordService {
    private final String secret;
    private final Long expiration;

    public TokenResetPasswordService(String secret,
                                     Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateResetToken(Employee employee) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("${JWT_RESET_SECRET}")
                    .withSubject(employee.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating password reset token", exception);
        }
    }

    public String validateResetToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("${JWT_RESET_SECRET}")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while validating password reset token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
