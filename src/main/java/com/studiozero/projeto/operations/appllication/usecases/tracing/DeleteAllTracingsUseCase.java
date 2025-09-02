package com.studiozero.projeto.operations.appllication.usecases.tracing;

import com.studiozero.projeto.operations.domain.entities.Tracing;
import com.studiozero.projeto.operations.domain.repositories.TracingRepository;

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
