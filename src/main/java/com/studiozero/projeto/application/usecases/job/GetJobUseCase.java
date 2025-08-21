package com.studiozero.projeto.application.usecases.job;

import java.util.UUID;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;

public class GetJobUseCase {
    private final JobRepository jobRepository;

    public GetJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job execute(UUID id) {
        return jobRepository.findById(id);
    }
}
