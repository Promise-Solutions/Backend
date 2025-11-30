package com.studiozero.projeto.infrastructure.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;

public class ValidateTokenService {
    private final String SECRET;
    private static final String ISSUER = "studiozero-auth";

    public ValidateTokenService(@Value("${app.jwt.secret}") String SECRET) {
        this.SECRET = SECRET;
    }

    public String execute(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "Invalid or expired token";
        }
    }
}
