package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
