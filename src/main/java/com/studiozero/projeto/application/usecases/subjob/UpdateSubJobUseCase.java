package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;

public class UpdateSubJobUseCase {
    private final SubJobRepository subJobRepository;
    private final JobRepository jobRepository;

    public UpdateSubJobUseCase(SubJobRepository subJobRepository, JobRepository jobRepository) {
        this.subJobRepository = subJobRepository;
        this.jobRepository = jobRepository;
    }

    public SubJob execute(SubJob subJob) {
        Job existingJob = subJob.getJob();

        boolean existsRoomConflict = subJobRepository.existsRoomConflictExceptId(
                subJob.getJob().getCategory(), subJob.getDate(), subJob.getStartTime(),
                subJob.getExpectedEndTime(), subJob.getId());

        if (subJob.getNeedsRoom() && existsRoomConflict) {
            throw new IllegalStateException(
                    "Há uma sala já em uso no dia " +subJob.getDate()+
                    " do horário " +subJob.getStartTime()+
                    "ao " +subJob.getExpectedEndTime()
            );
        }

        existingJob.updateSubJob(subJob);
        existingJob.calculateTotalValue();

        subJobRepository.save(subJob);
        return subJob;
    }
}
