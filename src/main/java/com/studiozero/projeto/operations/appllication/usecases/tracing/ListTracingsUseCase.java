package com.studiozero.projeto.operations.appllication.usecases.tracing;

import com.studiozero.projeto.operations.domain.entities.Tracing;
import com.studiozero.projeto.operations.domain.repositories.TracingRepository;
import java.util.List;

public class ListTracingsUseCase {
    private final TracingRepository tracingRepository;

    public ListTracingsUseCase(TracingRepository tracingRepository) {
        this.tracingRepository = tracingRepository;
    }

    public List<Tracing> execute() {
        return tracingRepository.findAll();
    }
}
