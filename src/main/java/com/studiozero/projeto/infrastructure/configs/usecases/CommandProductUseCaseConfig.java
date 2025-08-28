package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.CommandProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.application.usecases.commandproduct.GetCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.CreateCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.ListCommandProductsUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.UpdateCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.DeleteCommandProductUseCase;

@Configuration
public class CommandProductUseCaseConfig {
    @Bean
    public GetCommandProductUseCase getCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository) {
        return new GetCommandProductUseCase(commandProductRepository);
    }

    @Bean
    public CreateCommandProductUseCase createCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository) {
        return new CreateCommandProductUseCase(commandProductRepository);
    }

    @Bean
    public ListCommandProductsUseCase listCommandProductsUseCase(CommandProductRepositoryImpl commandProductRepository) {
        return new ListCommandProductsUseCase(commandProductRepository);
    }

    @Bean
    public UpdateCommandProductUseCase updateCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository) {
        return new UpdateCommandProductUseCase(commandProductRepository);
    }

    @Bean
    public DeleteCommandProductUseCase deleteCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository) {
        return new DeleteCommandProductUseCase(commandProductRepository);
    }
}

