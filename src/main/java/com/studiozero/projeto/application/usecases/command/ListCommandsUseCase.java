package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;

import java.util.List;

public class ListCommandsUseCase {
    private final CommandRepository commandRepository;

    public ListCommandsUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public List<Command> execute() {
        return commandRepository.findAll();
    }

}
