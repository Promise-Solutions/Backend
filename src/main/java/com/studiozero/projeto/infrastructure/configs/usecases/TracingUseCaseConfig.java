package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.TracingRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.application.usecases.tracing.CreateTracingUseCase;
import com.studiozero.projeto.application.usecases.tracing.DeleteAllTracingsUseCase;
import com.studiozero.projeto.application.usecases.tracing.ListTracingsUseCase;
import com.studiozero.projeto.application.usecases.tracing.SetTracingUseCase;

@Configuration
public class TracingUseCaseConfig {
    @Bean
    public CreateTracingUseCase createTracingUseCase(TracingRepositoryImpl tracingRepository) {
        return new CreateTracingUseCase(tracingRepository);
    }

    @Bean
    public DeleteAllTracingsUseCase deleteAllTracingsUseCase(TracingRepositoryImpl tracingRepository) {
        return new DeleteAllTracingsUseCase(tracingRepository);
    }

    @Bean
    public ListTracingsUseCase listTracingsUseCase(TracingRepositoryImpl tracingRepository) {
        return new ListTracingsUseCase(tracingRepository);
    }

    @Bean
    public SetTracingUseCase setTracingUseCase(TracingRepositoryImpl tracingRepository) {
        return new SetTracingUseCase(tracingRepository);
    }
}

