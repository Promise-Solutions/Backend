package com.studiozero.projeto.operations.appllication.usecases.job;

import java.util.UUID;

import com.studiozero.projeto.operations.domain.repositories.JobRepository;

public class DeleteJobUseCase {
    private final JobRepository jobRepository;

    public DeleteJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(UUID id) {
        jobRepository.deleteById(id);
    }
}
