package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.UUID;

public class DeleteSubJobUseCase {
    private final SubJobRepository subJobRepository;
    private final JobRepository jobRepository;

    public DeleteSubJobUseCase(SubJobRepository subJobRepository, JobRepository jobRepository) {
        this.subJobRepository = subJobRepository;
        this.jobRepository = jobRepository;
    }

    public SubJob execute(UUID subJobId) {
        Job job = subJobRepository.findJobBySubJobId(subJobId);
        if (job == null) {
            throw new IllegalArgumentException("SubJob not found");
        }
        SubJob subJobRemoved = job.removeSubJob(subJobId);

        jobRepository.save(job);
        return subJobRemoved;
    }
}
