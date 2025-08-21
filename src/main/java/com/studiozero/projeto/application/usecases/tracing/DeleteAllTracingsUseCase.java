package com.studiozero.projeto.application.usecases.tracing;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.domain.repositories.TracingRepository;

public class DeleteAllTracingsUseCase {
    private final TracingRepository tracingRepository;

    public DeleteAllTracingsUseCase(TracingRepository tracingRepository) {
        this.tracingRepository = tracingRepository;
    }

    public void execute() {
        Tracing lastTracing = tracingRepository.findTopByOrderByIdDesc();
        if (lastTracing == null) {
            throw new IllegalArgumentException("No tracing records found.");
        }
        tracingRepository.deleteAllByIdNot(lastTracing.getId());
    }
}
