package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.SubJobRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaSubJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.SubJobRepository;
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
    public CreateSubJobUseCase createSubJobUseCase(SubJobRepository subJobRepository) {
        return new CreateSubJobUseCase(subJobRepository);
    }

    @Bean
    public DeleteSubJobUseCase deleteSubJobUseCase(SubJobRepository subJobRepository) {
        return new DeleteSubJobUseCase(subJobRepository);
    }

    @Bean
    public GetSubJobUseCase getSubJobUseCase(SubJobRepository subJobRepository) {
        return new GetSubJobUseCase(subJobRepository);
    }

    @Bean
    public ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase(SubJobRepository subJobRepository) {
        return new ListSubJobsByFkServiceUseCase(subJobRepository);
    }

    @Bean
    public ListSubJobsUseCase listSubJobsUseCase(SubJobRepository subJobRepository) {
        return new ListSubJobsUseCase(subJobRepository);
    }

    @Bean
    public UpdateSubJobStatusUseCase updateSubJobStatusUseCase(SubJobRepository subJobRepository) {
        return new UpdateSubJobStatusUseCase(subJobRepository);
    }

    @Bean
    public UpdateSubJobUseCase updateSubJobUseCase(SubJobRepository subJobRepository) {
        return new UpdateSubJobUseCase(subJobRepository);
    }

    @Bean
    public SubJobRepositoryImpl subJobRepositoryImpl(JpaSubJobRepository jpaSubJobRepository) {
        return new SubJobRepositoryImpl(jpaSubJobRepository);
    }
}

