package com.studiozero.projeto.application.usecases.job;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class ListJobsUseCase {
    private final JobRepository jobRepository;

    public ListJobsUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Page<Job> execute(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public List<Job> executeByClient(UUID fkClient) {
        return jobRepository.findAllByClientId(fkClient);
    }
}
