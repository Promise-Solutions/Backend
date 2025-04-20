package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandMapper {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public CommandMapper(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    public Command toEntity(CommandRequestDTO dto) {
        Command command = new Command();

        if (dto.getFkClient() != null) {
            Client client = clientRepository.findById(dto.getFkClient()).orElse(null);
            command.setClient(client);

        }

        Employee employee = employeeRepository.findById(dto.getFkEmployee())
                .orElseThrow(() -> new NotFoundException("FkEmployee not found"));

        command.setOpeningDateTime(dto.getOpeningDateTime());
        command.setClosingDateTime(dto.getClosingDateTime());
        command.setDiscount(dto.getDiscount());
        command.setTotalValue(dto.getTotalValue());
        command.setEmployee(employee);
        command.setStatus(dto.getStatus());
        return command;
    }

    public static CommandResponseDTO toDTO(Command command) {
        if (command == null) {
            return null;
        }
        CommandResponseDTO dto = new CommandResponseDTO();
        dto.setId(command.getId());
        dto.setOpeningDateTime(command.getOpeningDateTime());
        dto.setClosingDateTime(command.getClosingDateTime());
        dto.setDiscount(command.getDiscount());
        dto.setTotalValue(command.getTotalValue());
        dto.setFkClient(command.getClient() != null ? command.getClient().getId() : null);
        dto.setFkEmployee(command.getEmployee() != null ? command.getEmployee().getId() : null);
        dto.setStatus(command.getStatus());
        return dto;
    }

    public static List<CommandResponseDTO> toListDtos(List<Command> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(CommandMapper::toDTO)
                .toList();
    }

    public Command toEntity(CommandRequestDTO dto, Integer id) {
        if (dto == null) {
            return null;
        }

        Command command = new Command();

        if (dto.getFkClient() != null) {
            Client client = clientRepository.findById(dto.getFkClient()).orElse(null);
            command.setClient(client);

        }

        Employee employee = employeeRepository.findById(dto.getFkEmployee())
                .orElseThrow(() -> new NotFoundException("FkEmployee not found"));


        command.setId(id);
        command.setOpeningDateTime(dto.getOpeningDateTime());
        command.setClosingDateTime(dto.getClosingDateTime());
        command.setDiscount(dto.getDiscount());
        command.setTotalValue(dto.getTotalValue());
        command.setEmployee(employee);
        command.setStatus(dto.getStatus());
        return command;
    }
}
