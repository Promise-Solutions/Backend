package com.studiozero.projeto.operations.appllication.usecases.tracing;

import com.studiozero.projeto.operations.domain.entities.Tracing;
import com.studiozero.projeto.operations.domain.repositories.TracingRepository;
import com.studiozero.projeto.shared.application.enums.Context;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SetTracingUseCase {
    private final TracingRepository tracingRepository;

    public SetTracingUseCase(TracingRepository tracingRepository) {
        this.tracingRepository = tracingRepository;
    }

    public Tracing execute(Context context) {
        Tracing tracing = new Tracing();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        tracing.setContext(context);
        tracing.setDateTime(zonedDateTime.toLocalDateTime());
        tracingRepository.save(tracing);
        return tracing;
    }
}
