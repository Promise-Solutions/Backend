package com.studiozero.projeto.application.usecases.job;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;
import java.util.List;
import java.util.UUID;

public class ListJobsUseCase {
    private final JobRepository jobRepository;

    public ListJobsUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> execute() {
        return jobRepository.findAll();
    }

    public List<Job> executeByClient(UUID fkClient) {
        return jobRepository.findAllByClientId(fkClient);
    }
}
