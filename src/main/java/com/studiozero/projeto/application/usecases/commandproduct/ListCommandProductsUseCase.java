package com.studiozero.projeto.application.usecases.commandproduct;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import java.util.List;

public class ListCommandProductsUseCase {
    private final CommandProductRepository commandProductRepository;

    public ListCommandProductsUseCase(CommandProductRepository commandProductRepository) {
        this.commandProductRepository = commandProductRepository;
    }

    public List<CommandProduct> execute(Integer fkComanda) {
        return commandProductRepository.findAllByCommand_Id(fkComanda);
    }
}
