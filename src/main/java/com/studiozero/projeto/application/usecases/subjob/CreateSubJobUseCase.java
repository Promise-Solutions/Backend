package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.UUID;

public class CreateSubJobUseCase {
    private final SubJobRepository subJobRepository;

    public CreateSubJobUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public SubJob execute(SubJob subJob) {
        if (subJob.getNeedsRoom() && subJobRepository.existsRoomConflict(
                subJob.getJob().getCategory(), subJob.getDate(), subJob.getStartTime(), subJob.getExpectedEndTime())) {
            throw new IllegalStateException("There is a room usage conflict");
        }
        subJobRepository.save(subJob);
        return subJob;
    }
}
