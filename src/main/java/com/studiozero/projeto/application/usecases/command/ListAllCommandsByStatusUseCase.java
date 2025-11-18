package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;

import java.util.List;

public class ListAllCommandsByStatusUseCase {
    private final CommandRepository commandRepository;

    public ListAllCommandsByStatusUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public List<Command> execute(Status status) {
        return commandRepository.findAllByStatus(status);
    }
}
