package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;

public class CreateCommandUseCase {
    private final CommandRepository commandRepository;

    public CreateCommandUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public Command execute(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Comanda inválida");
        }
        // Adicione validações de negócio aqui se necessário
        commandRepository.save(command);
        return command;
    }
}
