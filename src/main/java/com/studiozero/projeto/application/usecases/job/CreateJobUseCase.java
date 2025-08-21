package com.studiozero.projeto.application.usecases.job;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;

public class CreateJobUseCase {
    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(Job job) {
        if (job == null || job.getId() == null || job.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Job inválido");
        }
        jobRepository.save(job);
    }
}
