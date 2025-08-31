package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.application.enums.Status;
import java.util.UUID;

public class UpdateSubJobStatusUseCase {
    private final SubJobRepository subJobRepository;
    private final JobRepository jobRepository;

    public UpdateSubJobStatusUseCase(SubJobRepository subJobRepository, JobRepository jobRepository) {
        this.subJobRepository = subJobRepository;
        this.jobRepository = jobRepository;
    }
    public SubJob execute(UUID subJobId, Status newStatus, UUID jobId) {
        Job job = jobRepository.findById(jobId);
        if(job == null) {
            throw new IllegalArgumentException("Serviço não encontrado para mudar status");
        }

        SubJob subJob = job.getSubJobs()
                .stream()
                .filter(s -> s.getId().equals(subJobId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("subserviço não encontrado para mudar status"));

        subJob.changeStatus(newStatus);
        job.evaluateStatus();

        jobRepository.save(job);
        return subJob;
    }
}
