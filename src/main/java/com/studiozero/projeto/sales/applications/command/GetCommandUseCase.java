package com.studiozero.projeto.sales.applications.command;

import com.studiozero.projeto.sales.domain.entities.Command;
import com.studiozero.projeto.sales.domain.repository.CommandRepository;

public class GetCommandUseCase {
    private final CommandRepository commandRepository;

    public GetCommandUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public Command execute(Integer id) {
        Command command = commandRepository.findById(id);
        if (command == null) {
            throw new IllegalArgumentException("Comanda não encontrada");
        }
        return command;
    }
}
