package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.job.*;
import com.studiozero.projeto.infrastructure.repositories.Implements.JobRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobUseCaseConfig {
    @Bean
    public CreateJobUseCase createJobUseCase(JobRepositoryImpl jobRepository) {
        return new CreateJobUseCase(jobRepository);
    }

    @Bean
    public DeleteJobUseCase deleteJobUseCase(JobRepositoryImpl jobRepository) {
        return new DeleteJobUseCase(jobRepository);
    }

    @Bean
    public GetJobUseCase getJobUseCase(JobRepositoryImpl jobRepository) {
        return new GetJobUseCase(jobRepository);
    }

    @Bean
    public ListJobsUseCase listJobsUseCase(JobRepositoryImpl jobRepository) {
        return new ListJobsUseCase(jobRepository);
    }

    @Bean
    public UpdateJobUseCase updateJobUseCase(JobRepositoryImpl jobRepository) {
        return new UpdateJobUseCase(jobRepository);
    }
}

