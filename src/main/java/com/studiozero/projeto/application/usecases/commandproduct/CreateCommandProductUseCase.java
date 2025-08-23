package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;

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
