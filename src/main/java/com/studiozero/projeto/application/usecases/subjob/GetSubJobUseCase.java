package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.UUID;

public class GetSubJobUseCase {
    private final SubJobRepository subJobRepository;

    public GetSubJobUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public SubJob execute(UUID id) {
        SubJob subJob = subJobRepository.findById(id);
        if (subJob == null) {
            throw new IllegalArgumentException("Sub job not found");
        }
        return subJob;
    }
}
