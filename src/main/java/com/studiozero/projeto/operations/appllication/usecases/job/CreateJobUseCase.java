package com.studiozero.projeto.operations.appllication.usecases.job;

import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.domain.repositories.JobRepository;

import java.util.UUID;

public class CreateJobUseCase {
    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job inválido");
        }

        if (job.getId() == null) {
            job.setId(UUID.randomUUID());
        }
        jobRepository.save(job);
    }
}
