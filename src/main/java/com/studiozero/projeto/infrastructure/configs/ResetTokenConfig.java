package com.studiozero.projeto.infrastructure.configs;

import com.studiozero.projeto.infrastructure.services.ResetPassword.TokenResetPasswordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetTokenConfig {

    @Bean
    public TokenResetPasswordService tokenResetPasswordService(@Value("${JWT_RESET_SECRET}") String secret,
                                                          @Value("${EXPIRATION_TIME_RESET}") Long expiration) {
        return new TokenResetPasswordService(secret, expiration);
    }

}
