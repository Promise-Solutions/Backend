package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.application.enums.Status;
import java.util.List;

public class ListCommandsUseCase {
    private final CommandRepository commandRepository;

    public ListCommandsUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public List<Command> execute() {
        // Supondo que queremos todos os comandos, independente do status
        return commandRepository.findAllByStatus(null);
    }

    public List<Command> execute(Status status) {
        return commandRepository.findAllByStatus(status);
    }
}
