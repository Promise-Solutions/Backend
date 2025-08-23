package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;

public class DeleteCommandUseCase {
    private final CommandRepository commandRepository;

    public DeleteCommandUseCase(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public void execute(Integer id) {
        Command command = commandRepository.findById(id);
        if (command == null) {
            throw new IllegalArgumentException("Comanda não encontrada");
        }
        commandRepository.deleteById(id);
    }
}
