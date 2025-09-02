package com.studiozero.projeto.sales.applications.command;

import com.studiozero.projeto.sales.domain.entities.Command;
import com.studiozero.projeto.sales.domain.repository.CommandRepository;

public class UpdateCommandUseCase {
    private final CommandRepository commandRepository;

    public UpdateCommandUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public Command execute(Command command) {
        if (command == null || command.getId() == null) {
            throw new IllegalArgumentException("Comanda inválida");
        }
        Command existing = commandRepository.findById(command.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Comanda não encontrada");
        }
        commandRepository.save(command);
        return command;
    }
}
