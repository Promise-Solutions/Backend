package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.application.usecases.command.CommandTotalCalculator;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;

public class UpdateCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final UpdateCommandUseCase updateCommandUseCase;
    private final GetCommandUseCase getCommandUseCase;
    private final CommandTotalCalculator commandTotalCalculator;

    public UpdateCommandProductUseCase(CommandProductRepository commandProductRepository, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, UpdateCommandUseCase updateCommandUseCase, GetCommandUseCase getCommandUseCase, CommandTotalCalculator commandTotalCalculator) {
        this.commandProductRepository = commandProductRepository;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.updateCommandUseCase = updateCommandUseCase;
        this.getCommandUseCase = getCommandUseCase;
        this.commandTotalCalculator = commandTotalCalculator;
    }

    public CommandProduct execute(CommandProduct updated) {
        if (updated == null || updated.getId() == null) {
            throw new IllegalArgumentException("CommandProduct and id cannot be null");
        }

        CommandProduct existing = commandProductRepository.findById(updated.getId());
        if (existing == null) {
            throw new IllegalArgumentException("CommandProduct not found");
        }

        Product product = getProductUseCase.execute(updated.getProduct().getId());

        Integer diferenca = updated.getProductQuantity() - existing.getProductQuantity();
        Integer novoEstoque = product.getQuantity() - diferenca;

        if (novoEstoque < 0) {
            throw new IllegalArgumentException("Not enough product in stock");
        }

        product.setQuantity(novoEstoque);
        updateProductUseCase.execute(product);
        commandProductRepository.save(updated);
        var command = getCommandUseCase.execute(updated.getCommand().getId());
        double totalValue = commandTotalCalculator.calculateTotalValue(command.getId());
        command.setTotalValue(totalValue);
        updateCommandUseCase.execute(command);
        return updated;
    }
}
