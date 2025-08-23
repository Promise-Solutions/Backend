package com.studiozero.projeto.infrastructure.configs;

import com.studiozero.projeto.infrastructure.services.GenerateTokenService;
import com.studiozero.projeto.infrastructure.services.ValidateTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {
    @Bean
    public ValidateTokenService validateTokenUseCase(@Value("${JWT_SECRET:studio-zero-key}") String secret) {
        return new ValidateTokenService(secret);
    }

    @Bean
    public GenerateTokenService generateTokenUseCase(@Value("${JWT_SECRET:studio-zero-key}") String secret) {
        return new GenerateTokenService(secret);
    }
}
