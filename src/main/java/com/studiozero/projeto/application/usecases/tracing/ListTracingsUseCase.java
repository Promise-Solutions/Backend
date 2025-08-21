package com.studiozero.projeto.application.usecases.tracing;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.domain.repositories.TracingRepository;
import java.util.List;

public class ListTracingsUseCase {
    private final TracingRepository tracingRepository;

    public ListTracingsUseCase(TracingRepository tracingRepository) {
        this.tracingRepository = tracingRepository;
    }

    public List<Tracing> execute() {
        return tracingRepository.listAll();
    }
}
