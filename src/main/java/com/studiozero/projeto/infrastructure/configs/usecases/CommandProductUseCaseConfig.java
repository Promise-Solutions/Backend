package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.command.CommandTotalCalculator;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
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
    public CreateCommandProductUseCase createCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, UpdateCommandUseCase updateCommandUseCase, GetCommandUseCase getCommandUseCase, CommandTotalCalculator commandTotalCalculator) {
        return new CreateCommandProductUseCase(commandProductRepository, getProductUseCase, updateProductUseCase, updateCommandUseCase, getCommandUseCase, commandTotalCalculator);
    }

    @Bean
    public ListCommandProductsUseCase listCommandProductsUseCase(CommandProductRepositoryImpl commandProductRepository) {
        return new ListCommandProductsUseCase(commandProductRepository);
    }

    @Bean
    public UpdateCommandProductUseCase updateCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, UpdateCommandUseCase updateCommandUseCase, GetCommandUseCase getCommandUseCase, CommandTotalCalculator commandTotalCalculator) {
        return new UpdateCommandProductUseCase(commandProductRepository, getProductUseCase, updateProductUseCase, updateCommandUseCase, getCommandUseCase, commandTotalCalculator);
    }

    @Bean
    public DeleteCommandProductUseCase deleteCommandProductUseCase(CommandProductRepositoryImpl commandProductRepository, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, UpdateCommandUseCase updateCommandUseCase, GetCommandUseCase getCommandUseCase, CommandTotalCalculator commandTotalCalculator) {
        return new DeleteCommandProductUseCase(commandProductRepository, getProductUseCase, updateProductUseCase, updateCommandUseCase, getCommandUseCase, commandTotalCalculator);
    }


}

