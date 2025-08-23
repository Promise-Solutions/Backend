package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.web.handlers.ConflictException;

public class CreateCommandUseCase {
    private final CommandRepository commandRepository;

    public CreateCommandUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public Command execute(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Comanda inválida");
        }

        if (commandRepository.existsByCommandNumber(command.getCommandNumber())) {
            throw new ConflictException("Comanda com esse número já existe");
        }

        if(commandRepository.existsByClientId(command.getClient().getId())) {
            throw new ConflictException("Cliente já possui uma comanda aberta");
        }
        commandRepository.save(command);
        return command;
    }
}
