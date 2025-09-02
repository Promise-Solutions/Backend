package com.studiozero.projeto.sales.applications.commandproduct;

import com.studiozero.projeto.sales.domain.entities.CommandProduct;
import com.studiozero.projeto.sales.domain.repository.CommandProductRepository;

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
