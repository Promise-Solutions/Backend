package com.studiozero.projeto.operations.appllication.usecases.job;

import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.domain.repositories.JobRepository;

public class UpdateJobUseCase {
    private final JobRepository jobRepository;

    public UpdateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(Job job) {
        if (job == null || job.getId() == null || job.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Job inválido");
        }
        jobRepository.save(job);
    }
}
