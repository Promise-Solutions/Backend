package com.studiozero.projeto.application.usecases.tracing;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.domain.repositories.TracingRepository;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CreateTracingUseCase {
    private final TracingRepository tracingRepository;

    public CreateTracingUseCase(TracingRepository tracingRepository) {
        this.tracingRepository = tracingRepository;
    }

    public Tracing execute(Tracing tracing) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        tracing.setDateTime(zonedDateTime.toLocalDateTime());
        tracingRepository.save(tracing);
        return tracing;
    }
}
