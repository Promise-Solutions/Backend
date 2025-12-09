package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.application.usecases.command.CommandTotalCalculator;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;

public class CreateCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final UpdateCommandUseCase updateCommandUseCase;
    private final GetCommandUseCase getCommandUseCase;
    private final CommandTotalCalculator commandTotalCalculator;

    public CreateCommandProductUseCase(CommandProductRepository commandProductRepository, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, UpdateCommandUseCase updateCommandUseCase, GetCommandUseCase getCommandUseCase, CommandTotalCalculator commandTotalCalculator) {
        this.commandProductRepository = commandProductRepository;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.updateCommandUseCase = updateCommandUseCase;
        this.getCommandUseCase = getCommandUseCase;
        this.commandTotalCalculator = commandTotalCalculator;
    }

    public CommandProduct execute(CommandProduct commandProduct) {
        if (commandProduct == null) {
            throw new IllegalArgumentException("CommandProduct cannot be null");
        }

        Product product = getProductUseCase.execute(commandProduct.getProduct().getId());

        if (product.getQuantity() < commandProduct.getProductQuantity()) {
            throw new IllegalArgumentException("Insufficient product quantity");
        }
        product.setQuantity(product.getQuantity() - commandProduct.getProductQuantity());

        var command = getCommandUseCase.execute(commandProduct.getCommand().getId());
        if(command.getInternal()) {
            command.setTotalValue(command.getTotalValue() + (product.getInternalValue() * commandProduct.getProductQuantity()));
        } else {
            command.setTotalValue(command.getTotalValue() + (product.getClientValue() * commandProduct.getProductQuantity()));
        }
        updateProductUseCase.execute(product);
        updateCommandUseCase.execute(command);

        commandProductRepository.save(commandProduct);

        double totalValue = commandTotalCalculator.calculateTotalValue(command.getId());

        command.setTotalValue(totalValue);
        updateCommandUseCase.execute(command);
        return commandProduct;
    }
}
