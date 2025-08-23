package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;

public class UpdateSubJobUseCase {
    private final SubJobRepository subJobRepository;

    public UpdateSubJobUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public SubJob execute(SubJob subJob) {
        SubJob existing = subJobRepository.findById(subJob.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Sub job not found");
        }
        if (subJob.getNeedsRoom() && subJobRepository.existsRoomConflictExceptId(
                subJob.getJob().getCategory(), subJob.getDate(), subJob.getStartTime(),
                subJob.getExpectedEndTime(), subJob.getId())) {
            throw new IllegalStateException("There is a room usage conflict");
        }
        subJobRepository.save(subJob);
        return subJob;
    }
}
