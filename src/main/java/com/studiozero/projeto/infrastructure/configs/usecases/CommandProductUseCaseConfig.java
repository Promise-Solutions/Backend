package com.studiozero.projeto.infrastructure.configs.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import com.studiozero.projeto.application.usecases.commandproduct.GetCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.CreateCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.ListCommandProductsUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.UpdateCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.DeleteCommandProductUseCase;

@Configuration
public class CommandProductUseCaseConfig {
    @Bean
    public GetCommandProductUseCase getCommandProductUseCase(CommandProductRepository commandProductRepository) {
        return new GetCommandProductUseCase(commandProductRepository);
    }

    @Bean
    public CreateCommandProductUseCase createCommandProductUseCase(CommandProductRepository commandProductRepository) {
        return new CreateCommandProductUseCase(commandProductRepository);
    }

    @Bean
    public ListCommandProductsUseCase listCommandProductsUseCase(CommandProductRepository commandProductRepository) {
        return new ListCommandProductsUseCase(commandProductRepository);
    }

    @Bean
    public UpdateCommandProductUseCase updateCommandProductUseCase(CommandProductRepository commandProductRepository) {
        return new UpdateCommandProductUseCase(commandProductRepository);
    }

    @Bean
    public DeleteCommandProductUseCase deleteCommandProductUseCase(CommandProductRepository commandProductRepository) {
        return new DeleteCommandProductUseCase(commandProductRepository);
    }
}

