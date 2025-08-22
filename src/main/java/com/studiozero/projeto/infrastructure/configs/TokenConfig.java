package com.studiozero.projeto.infrastructure.configs;

import com.studiozero.projeto.application.usecases.token.ValidateTokenUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.modulith.Published;

@Configuration
public class TokenConfig {
    @Bean
    @Published
    public ValidateTokenUseCase validateTokenUseCase(@Value("${JWT_SECRET:studio-zero-key}") String secret) {
        return new ValidateTokenUseCase(secret);
    }
}
