package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.infrastructure.repositories.Implements.JobRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.application.usecases.job.CalculateJobTotalValueUseCase;
import com.studiozero.projeto.application.usecases.job.CreateJobUseCase;
import com.studiozero.projeto.application.usecases.job.DeleteJobUseCase;
import com.studiozero.projeto.application.usecases.job.EvaluateJobStatusUseCase;
import com.studiozero.projeto.application.usecases.job.GetJobUseCase;
import com.studiozero.projeto.application.usecases.job.ListJobsUseCase;
import com.studiozero.projeto.application.usecases.job.UpdateJobUseCase;

@Configuration
public class JobUseCaseConfig {
    @Bean
    public CalculateJobTotalValueUseCase calculateJobTotalValueUseCase(JobRepository jobRepository, SubJobRepository subJobRepository) {
        return new CalculateJobTotalValueUseCase(jobRepository, subJobRepository);
    }

    @Bean
    public CreateJobUseCase createJobUseCase(JobRepository jobRepository) {
        return new CreateJobUseCase(jobRepository);
    }

    @Bean
    public DeleteJobUseCase deleteJobUseCase(JobRepository jobRepository) {
        return new DeleteJobUseCase(jobRepository);
    }

    @Bean
    public EvaluateJobStatusUseCase evaluateJobStatusUseCase(JobRepository jobRepository, SubJobRepository subJobRepository) {
        return new EvaluateJobStatusUseCase(jobRepository, subJobRepository);
    }

    @Bean
    public GetJobUseCase getJobUseCase(JobRepository jobRepository) {
        return new GetJobUseCase(jobRepository);
    }

    @Bean
    public ListJobsUseCase listJobsUseCase(JobRepository jobRepository) {
        return new ListJobsUseCase(jobRepository);
    }

    @Bean
    public UpdateJobUseCase updateJobUseCase(JobRepository jobRepository) {
        return new UpdateJobUseCase(jobRepository);
    }

    @Bean
    public JobRepositoryImpl jobRepositoryImpl(JpaJobRepository jpaJobRepository) {
        return new JobRepositoryImpl(jpaJobRepository);
    }
}

