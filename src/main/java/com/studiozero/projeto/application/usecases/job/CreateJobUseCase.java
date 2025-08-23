package com.studiozero.projeto.application.usecases.job;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;

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
