package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.application.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.application.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
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
        command.setCommandNumber(dto.getCommandNumber());
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
        dto.setCommandNumber(command.getCommandNumber());
        dto.setOpeningDateTime(command.getOpeningDateTime());
        dto.setClosingDateTime(command.getClosingDateTime());
        dto.setDiscount(command.getDiscount());
        dto.setTotalValue(command.getTotalValue());
        dto.setFkClient(command.getClient() != null ? command.getClient().getId() : null);
        dto.setFkEmployee(command.getEmployee() != null ? command.getEmployee().getId() : null);
        dto.setStatus(command.getStatus());
        dto.setIsInternal(command.getIsInternal());
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
        command.setCommandNumber(dto.getCommandNumber());
        command.setOpeningDateTime(dto.getOpeningDateTime());
        command.setClosingDateTime(dto.getClosingDateTime());
        command.setDiscount(dto.getDiscount());
        command.setTotalValue(dto.getTotalValue());
        command.setEmployee(employee);
        command.setStatus(dto.getStatus());
        return command;
    }

//    private Command mapCommonFields(CommandRequestDTO dto, Command command) {
//        if (dto.getFkClient() != null) {
//            Client client = clientRepository.findById(dto.getFkClient()).orElse(null);
//            command.setClient(client);
//        }
//
//        Employee employee = employeeRepository.findById(dto.getFkEmployee())
//                .orElseThrow(() -> new NotFoundException("FkEmployee not found"));
//
//        command.setCommandNumber(dto.getCommandNumber());
//        command.setOpeningDateTime(dto.getOpeningDateTime());
//        command.setClosingDateTime(dto.getClosingDateTime());
//        command.setDiscount(dto.getDiscount());
//        command.setTotalValue(dto.getTotalValue());
//        command.setEmployee(employee);
//        command.setStatus(dto.getStatus());
//
//        return command;
//    }
//
//    public Command toEntity(CommandRequestDTO dto) {
//        return mapCommonFields(dto, new Command());
//    }
//
//    public Command toEntity(CommandRequestDTO dto, Integer id) {
//        Command command = new Command();
//        command.setId(id);
//        return mapCommonFields(dto, command);
//    }

}
