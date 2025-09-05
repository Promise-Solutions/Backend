package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import java.util.List;

public class CommandTotalCalculator {
    private final CommandProductRepository commandProductRepository;

    public CommandTotalCalculator(CommandProductRepository commandProductRepository) {
        this.commandProductRepository = commandProductRepository;
    }

    public double calculateTotalValue(Integer commandId) {
        List<CommandProduct> products = commandProductRepository.findAllByCommand_Id(commandId);
        return products.stream()
                .mapToDouble(cp -> cp.getUnitValue() * cp.getProductQuantity())
                .sum();
    }
}

