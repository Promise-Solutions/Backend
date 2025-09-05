package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.application.usecases.command.CommandTotalCalculator;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;

public class DeleteCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final UpdateCommandUseCase updateCommandUseCase;
    private final GetCommandUseCase getCommandUseCase;
    private final CommandTotalCalculator commandTotalCalculator;

    public DeleteCommandProductUseCase(CommandProductRepository commandProductRepository, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, UpdateCommandUseCase updateCommandUseCase, GetCommandUseCase getCommandUseCase, CommandTotalCalculator commandTotalCalculator) {
        this.commandProductRepository = commandProductRepository;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.updateCommandUseCase = updateCommandUseCase;
        this.getCommandUseCase = getCommandUseCase;
        this.commandTotalCalculator = commandTotalCalculator;
    }

    public void execute(Integer id) {
        CommandProduct commandProduct = null;
        if (id != null) {
            commandProduct = commandProductRepository.findById(id);
        }
        if (commandProduct == null) {
            throw new IllegalArgumentException("CommandProduct not found");
        }

        Product product = getProductUseCase.execute(commandProduct.getProduct().getId());
        product.setQuantity(commandProduct.getProductQuantity() + product.getQuantity());
        var command = getCommandUseCase.execute(commandProduct.getCommand().getId());
        command.setTotalValue(command.getTotalValue() - (commandProduct.getProductQuantity() * commandProduct.getUnitValue()));
        updateCommandUseCase.execute(command);
        updateProductUseCase.execute(product);
        commandProductRepository.deleteById(id);

        double totalValue = commandTotalCalculator.calculateTotalValue(command.getId());

        command.setTotalValue(totalValue);
        updateCommandUseCase.execute(command);
    }
}
