package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.CommandProductRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaCommandProductRepository;
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

    @Bean
    public CommandProductRepositoryImpl commandProductRepositoryImpl(JpaCommandProductRepository jpaCommandProductRepository) {
        return new CommandProductRepositoryImpl(jpaCommandProductRepository);
    }
}

