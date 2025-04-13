package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.entities.Command;
import org.springframework.stereotype.Component;

@Component
public class CommandMapper {

    public Command toEntity(CommandRequestDTO dto) {
        Command command = new Command();
        command.setOpeningDateTime(dto.getOpeningDateTime());
        command.setClosingDateTime(dto.getClosingDateTime());
        command.setDiscount(dto.getDiscount());
        command.setTotalValue(dto.getTotalValue());
        command.setFkClient(dto.getFkClient());
        command.setFkEmployee(dto.getFkEmployee());
        command.setStatus(dto.getStatus());
        return command;
    }

    public CommandResponseDTO toDTO(Command command) {
        return new CommandResponseDTO(command);
    }
}
