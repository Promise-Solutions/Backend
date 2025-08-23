package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;

public class UpdateCommandProductUseCase {
    private final CommandProductRepository commandProductRepository;

    public UpdateCommandProductUseCase(CommandProductRepository commandProductRepository) {
        this.commandProductRepository = commandProductRepository;
    }

    public CommandProduct execute(CommandProduct updated) {
        if (updated == null || updated.getId() == null) {
            throw new IllegalArgumentException("CommandProduct and id cannot be null");
        }
        CommandProduct existing = commandProductRepository.findById(updated.getId());
        if (existing == null) {
            throw new IllegalArgumentException("CommandProduct not found");
        }
        commandProductRepository.save(updated);
        return updated;
    }
}
