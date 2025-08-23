package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;

public class GetCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;

    public GetCommandProductUseCase(CommandProductRepository commandProductRepository) {
        this.commandProductRepository = commandProductRepository;
    }

    public CommandProduct execute(Integer id) {
        CommandProduct commandProduct = commandProductRepository.findById(id);
        if (commandProduct == null) {
            throw new IllegalArgumentException("CommandProduct not found");
        }
        return commandProduct;
    }
}
