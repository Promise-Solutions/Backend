package com.studiozero.projeto.sales.applications.commandproduct;

import com.studiozero.projeto.sales.domain.entities.CommandProduct;
import com.studiozero.projeto.sales.domain.repository.CommandProductRepository;

public class DeleteCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;

    public DeleteCommandProductUseCase(CommandProductRepository commandProductRepository) {
        this.commandProductRepository = commandProductRepository;
    }

    public void execute(Integer id) {
        CommandProduct commandProduct = commandProductRepository.findById(id);
        if (commandProduct == null) {
            throw new IllegalArgumentException("CommandProduct not found");
        }
        commandProductRepository.deleteById(id);
    }
}
