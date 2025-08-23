package com.studiozero.projeto.infrastructure.configs.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.CreateCommandUseCase;
import com.studiozero.projeto.application.usecases.command.ListCommandsUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.command.DeleteCommandUseCase;

@Configuration
public class CommandUseCaseConfig {
    @Bean
    public GetCommandUseCase getCommandUseCase(CommandRepository commandRepository) {
        return new GetCommandUseCase(commandRepository);
    }

    @Bean
    public CreateCommandUseCase createCommandUseCase(CommandRepository commandRepository) {
        return new CreateCommandUseCase(commandRepository);
    }

    @Bean
    public ListCommandsUseCase listCommandsUseCase(CommandRepository commandRepository) {
        return new ListCommandsUseCase(commandRepository);
    }

    @Bean
    public UpdateCommandUseCase updateCommandUseCase(CommandRepository commandRepository) {
        return new UpdateCommandUseCase(commandRepository);
    }

    @Bean
    public DeleteCommandUseCase deleteCommandUseCase(CommandRepository commandRepository) {
        return new DeleteCommandUseCase(commandRepository);
    }
}

