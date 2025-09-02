package com.studiozero.projeto.operations.appllication.usecases.job;

import java.util.UUID;

import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.domain.repositories.JobRepository;

public class GetJobUseCase {
    private final JobRepository jobRepository;

    public GetJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job execute(UUID id) {
        return jobRepository.findById(id);
    }
}
