package com.studiozero.projeto.sales.infrastruture.usecase;

import com.studiozero.projeto.sales.infrastruture.repository.impl.CommandProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.sales.applications.commandproduct.GetCommandProductUseCase;
import com.studiozero.projeto.sales.applications.commandproduct.CreateCommandProductUseCase;
import com.studiozero.projeto.sales.applications.commandproduct.ListCommandProductsUseCase;
import com.studiozero.projeto.sales.applications.commandproduct.UpdateCommandProductUseCase;
import com.studiozero.projeto.sales.applications.commandproduct.DeleteCommandProductUseCase;

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

