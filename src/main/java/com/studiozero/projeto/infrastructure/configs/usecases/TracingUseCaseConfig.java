package com.studiozero.projeto.infrastructure.configs.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.TracingRepository;
import com.studiozero.projeto.application.usecases.tracing.CreateTracingUseCase;
import com.studiozero.projeto.application.usecases.tracing.DeleteAllTracingsUseCase;
import com.studiozero.projeto.application.usecases.tracing.ListTracingsUseCase;
import com.studiozero.projeto.application.usecases.tracing.SetTracingUseCase;

@Configuration
public class TracingUseCaseConfig {
    @Bean
    public CreateTracingUseCase createTracingUseCase(TracingRepository tracingRepository) {
        return new CreateTracingUseCase(tracingRepository);
    }

    @Bean
    public DeleteAllTracingsUseCase deleteAllTracingsUseCase(TracingRepository tracingRepository) {
        return new DeleteAllTracingsUseCase(tracingRepository);
    }

    @Bean
    public ListTracingsUseCase listTracingsUseCase(TracingRepository tracingRepository) {
        return new ListTracingsUseCase(tracingRepository);
    }

    @Bean
    public SetTracingUseCase setTracingUseCase(TracingRepository tracingRepository) {
        return new SetTracingUseCase(tracingRepository);
    }
}

