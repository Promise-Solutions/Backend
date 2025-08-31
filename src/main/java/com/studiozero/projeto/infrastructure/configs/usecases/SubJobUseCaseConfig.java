package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.JobRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.Implements.SubJobRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.application.usecases.subjob.CreateSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.DeleteSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.GetSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.ListSubJobsByFkServiceUseCase;
import com.studiozero.projeto.application.usecases.subjob.ListSubJobsUseCase;
import com.studiozero.projeto.application.usecases.subjob.UpdateSubJobStatusUseCase;
import com.studiozero.projeto.application.usecases.subjob.UpdateSubJobUseCase;

@Configuration
public class SubJobUseCaseConfig {
    @Bean
    public CreateSubJobUseCase createSubJobUseCase(SubJobRepositoryImpl subJobRepository, JobRepositoryImpl jobRepository) {
        return new CreateSubJobUseCase(subJobRepository, jobRepository);
    }

    @Bean
    public DeleteSubJobUseCase deleteSubJobUseCase(SubJobRepositoryImpl subJobRepository, JobRepositoryImpl jobRepository) {
        return new DeleteSubJobUseCase(subJobRepository, jobRepository);
    }

    @Bean
    public GetSubJobUseCase getSubJobUseCase(SubJobRepositoryImpl subJobRepository) {
        return new GetSubJobUseCase(subJobRepository);
    }

    @Bean
    public ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase(SubJobRepositoryImpl subJobRepository) {
        return new ListSubJobsByFkServiceUseCase(subJobRepository);
    }

    @Bean
    public ListSubJobsUseCase listSubJobsUseCase(SubJobRepositoryImpl subJobRepository) {
        return new ListSubJobsUseCase(subJobRepository);
    }

    @Bean
    public UpdateSubJobStatusUseCase updateSubJobStatusUseCase(SubJobRepositoryImpl subJobRepository, JobRepositoryImpl jobRepository) {
        return new UpdateSubJobStatusUseCase(subJobRepository, jobRepository);
    }

    @Bean
    public UpdateSubJobUseCase updateSubJobUseCase(SubJobRepositoryImpl subJobRepository, JobRepositoryImpl jobRepository) {
        return new UpdateSubJobUseCase(subJobRepository, jobRepository);
    }
}

