package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;

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
