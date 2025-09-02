package com.studiozero.projeto.sales.applications.commandproduct;

import com.studiozero.projeto.sales.domain.entities.CommandProduct;
import com.studiozero.projeto.sales.domain.repository.CommandProductRepository;

public class CreateCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;

    public CreateCommandProductUseCase(CommandProductRepository commandProductRepository) {
        this.commandProductRepository = commandProductRepository;
    }

    public CommandProduct execute(CommandProduct commandProduct) {
        if (commandProduct == null) {
            throw new IllegalArgumentException("CommandProduct cannot be null");
        }

        commandProductRepository.save(commandProduct);
        return commandProduct;
    }
}
