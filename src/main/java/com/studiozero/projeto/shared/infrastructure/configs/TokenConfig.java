package com.studiozero.projeto.shared.infrastructure.configs;

import com.studiozero.projeto.shared.infrastructure.services.GenerateTokenService;
import com.studiozero.projeto.shared.infrastructure.services.ValidateTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {
    @Bean
    public ValidateTokenService validateTokenUseCase(@Value("${JWT_SECRET}") String secret) {
        return new ValidateTokenService(secret);
    }

    @Bean
    public GenerateTokenService generateTokenUseCase(@Value("${JWT_SECRET}") String secret,
                                                     @Value("${EXPIRATION_TIME}") Long expiration) {
        return new GenerateTokenService(secret, expiration);
    }
}
