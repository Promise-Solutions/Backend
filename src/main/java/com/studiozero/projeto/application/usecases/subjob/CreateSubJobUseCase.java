package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class CreateSubJobUseCase {
    private final SubJobRepository subJobRepository;
    private final JobRepository jobRepository;

    public CreateSubJobUseCase(SubJobRepository subJobRepository, JobRepository jobRepository) {
        this.subJobRepository = subJobRepository;
        this.jobRepository = jobRepository;
    }

    public SubJob execute(SubJob subJob) {
        Job jobFound = jobRepository.findById(subJob.getJob().getId());

        if(jobFound == null) {
            throw new EntityNotFoundException("Serviço não encontrado para criar o subserviço");
        }
        if (subJob.getNeedsRoom() && subJobRepository.existsRoomConflict(
                subJob.getJob().getCategory(), subJob.getDate(), subJob.getStartTime(), subJob.getExpectedEndTime())) {
            throw new IllegalStateException("There is a room usage conflict");
        }

        if (subJob.getId() == null) {
            subJob.setId(UUID.randomUUID());
        }

        subJob.changeJob(jobFound);
        Job job = subJob.getJob();
        job.addSubJob(subJob);

        jobRepository.save(job);
        return subJob;
    }
}
