package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.CommandRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.CreateCommandUseCase;
import com.studiozero.projeto.application.usecases.command.ListCommandsUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.command.DeleteCommandUseCase;

@Configuration
public class CommandUseCaseConfig {
    @Bean
    public GetCommandUseCase getCommandUseCase(CommandRepositoryImpl commandRepository) {
        return new GetCommandUseCase(commandRepository);
    }

    @Bean
    public CreateCommandUseCase createCommandUseCase(CommandRepositoryImpl commandRepository) {
        return new CreateCommandUseCase(commandRepository);
    }

    @Bean
    public ListCommandsUseCase listCommandsUseCase(CommandRepositoryImpl commandRepository) {
        return new ListCommandsUseCase(commandRepository);
    }

    @Bean
    public UpdateCommandUseCase updateCommandUseCase(CommandRepositoryImpl commandRepository) {
        return new UpdateCommandUseCase(commandRepository);
    }

    @Bean
    public DeleteCommandUseCase deleteCommandUseCase(CommandRepositoryImpl commandRepository) {
        return new DeleteCommandUseCase(commandRepository);
    }
}

