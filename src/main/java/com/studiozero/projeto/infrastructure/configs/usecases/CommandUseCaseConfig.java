package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.command.*;
import com.studiozero.projeto.application.usecases.commandproduct.ListCommandProductsUseCase;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import com.studiozero.projeto.infrastructure.repositories.Implements.CommandProductRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.Implements.CommandRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public UpdateCommandUseCase updateCommandUseCase(CommandRepositoryImpl commandRepository, CommandTotalCalculator commandTotalCalculator) {
        return new UpdateCommandUseCase(commandRepository, commandTotalCalculator);
    }

    @Bean
    public DeleteCommandUseCase deleteCommandUseCase(CommandRepositoryImpl commandRepository) {
        return new DeleteCommandUseCase(commandRepository);
    }

    @Bean
    public CommandTotalCalculator commandTotalCalculator(CommandProductRepositoryImpl commandProductRepository) {
        return new CommandTotalCalculator(commandProductRepository);
    }
}

