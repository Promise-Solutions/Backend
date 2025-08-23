package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.UUID;

public class DeleteSubJobUseCase {
    private final SubJobRepository subJobRepository;

    public DeleteSubJobUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public void execute(UUID subJobId) {
        SubJob subJob = subJobRepository.findById(subJobId);
        if (subJob == null) {
            throw new IllegalArgumentException("SubJob not found");
        }
        subJobRepository.deleteById(subJobId);
    }
}
