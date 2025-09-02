package com.studiozero.projeto.operations.appllication.usecases.subjob;

import com.studiozero.projeto.operations.domain.entities.SubJob;
import com.studiozero.projeto.operations.domain.repositories.SubJobRepository;
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
