package com.studiozero.projeto.sales.applications.commandproduct;

import com.studiozero.projeto.sales.domain.entities.CommandProduct;
import com.studiozero.projeto.sales.domain.repository.CommandProductRepository;
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
