package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.web.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.web.dtos.response.CommandResponseDTO;
import java.util.List;

public class CommandMapper {
    public static Command toDomain(CommandRequestDTO dto, Client client, Employee employee) {
        if (dto == null) return null;
        return new Command(
            null,
            dto.getCommandNumber(),
            dto.getOpeningDateTime(),
            dto.getClosingDateTime(),
            dto.getDiscount(),
            dto.getTotalValue(),
            client,
            employee,
            null,
            dto.getStatus()
        );
    }

    public static Command toDomain(CommandRequestDTO dto, Integer id, Client client, Employee employee) {
        if (dto == null) return null;
        return new Command(
            id,
            dto.getCommandNumber(),
            dto.getOpeningDateTime(),
            dto.getClosingDateTime(),
            dto.getDiscount(),
            dto.getTotalValue(),
            client,
            employee,
            null,
            dto.getStatus()
        );
    }

    public static CommandResponseDTO toDTO(Command command) {
        if (command == null) return null;
        return new CommandResponseDTO(
            command.getId(),
            command.getCommandNumber(),
            command.getOpeningDateTime(),
            command.getClosingDateTime(),
            command.getDiscount(),
            command.getTotalValue(),
            command.getClient() != null ? command.getClient().getId() : null,
            command.getEmployee() != null ? command.getEmployee().getId() : null,
            command.getStatus(),
            command.getIsInternal()
        );
    }

    public static List<CommandResponseDTO> toDTOList(List<Command> commands) {
        if (commands == null) return null;
        return commands.stream().map(CommandMapper::toDTO).toList();
    }
}
