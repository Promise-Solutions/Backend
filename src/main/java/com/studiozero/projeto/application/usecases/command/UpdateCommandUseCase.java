package com.studiozero.projeto.application.usecases.command;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;

public class UpdateCommandUseCase {
    private final CommandRepository commandRepository;
    private final CommandTotalCalculator commandTotalCalculator;

    public UpdateCommandUseCase(CommandRepository commandRepository, CommandTotalCalculator commandTotalCalculator) {
        this.commandRepository = commandRepository;
        this.commandTotalCalculator = commandTotalCalculator;
    }

    public Command execute(Command command) {
        if (command == null || command.getId() == null) {
            throw new IllegalArgumentException("Comanda inválida");
        }
        Command existing = commandRepository.findById(command.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Comanda não encontrada");
        }

        if (command.getClient() == null) {
            command.setInternal(true);
        } else {
            command.setInternal(false);
        }

        double totalValue = commandTotalCalculator.calculateTotalValue(command.getId());
        if (command.getStatus() == Status.OPEN) {
            command.setDiscount(0.0);
            command.setTotalValue(totalValue);
        } else if (command.getStatus() == Status.CLOSED) {
            double desconto = command.getDiscount();
            if (desconto > 1) {
                desconto = desconto / 100;
            }
            double totalComDesconto = totalValue * (1 - desconto);
            if (totalComDesconto < 0) {
                throw new IllegalArgumentException("Total value cannot be negative");
            }
            command.setTotalValue(totalComDesconto);
        }

        commandRepository.save(command);
        return command;
    }
}
