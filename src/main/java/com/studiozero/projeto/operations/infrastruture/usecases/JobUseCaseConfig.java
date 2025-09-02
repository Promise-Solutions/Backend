package com.studiozero.projeto.operations.infrastruture.usecases;

import com.studiozero.projeto.operations.infrastruture.repository.impl.JobRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.studiozero.projeto.application.usecases.job.CalculateJobTotalValueUseCase;
import com.studiozero.projeto.operations.appllication.usecases.job.CreateJobUseCase;
import com.studiozero.projeto.operations.appllication.usecases.job.DeleteJobUseCase;
//import com.studiozero.projeto.application.usecases.job.EvaluateJobStatusUseCase;
import com.studiozero.projeto.operations.appllication.usecases.job.GetJobUseCase;
import com.studiozero.projeto.operations.appllication.usecases.job.ListJobsUseCase;
import com.studiozero.projeto.operations.appllication.usecases.job.UpdateJobUseCase;

@Configuration
public class JobUseCaseConfig {
//    @Bean
//    public CalculateJobTotalValueUseCase calculateJobTotalValueUseCase(JobRepositoryImpl jobRepository, SubJobRepositoryImpl subJobRepository) {
//        return new CalculateJobTotalValueUseCase(jobRepository, subJobRepository);
//    }

    @Bean
    public CreateJobUseCase createJobUseCase(JobRepositoryImpl jobRepository) {
        return new CreateJobUseCase(jobRepository);
    }

    @Bean
    public DeleteJobUseCase deleteJobUseCase(JobRepositoryImpl jobRepository) {
        return new DeleteJobUseCase(jobRepository);
    }

//    @Bean
//    public EvaluateJobStatusUseCase evaluateJobStatusUseCase(JobRepositoryImpl jobRepository, SubJobRepositoryImpl subJobRepository) {
//        return new EvaluateJobStatusUseCase(jobRepository, subJobRepository);
//    }

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

