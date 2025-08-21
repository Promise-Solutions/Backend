package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.application.enums.Status;
import java.util.UUID;

public class UpdateSubJobStatusUseCase {
    private final SubJobRepository subJobRepository;

    public UpdateSubJobStatusUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public SubJob execute(UUID id, Status status) {
        SubJob subJob = subJobRepository.findById(id);
        if (subJob == null) {
            throw new IllegalArgumentException("Sub job not found");
        }
        subJob.setStatus(status);
        subJobRepository.save(subJob);
        return subJob;
    }
}
