package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.web.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.web.dtos.response.CommandResponseDTO;
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
        if (dto == null)
            return null;
        Client client = null;
        if (dto.getFkClient() != null) {
            client = clientRepository.findById(dto.getFkClient());
        }
        Employee employee = null;
        if (dto.getFkEmployee() != null) {
            employee = employeeRepository.findById(dto.getFkEmployee());
        }
        if (employee == null) {
            throw new RuntimeException("FkEmployee not found");
        }
        return new Command(
                0, // id dummy, será sobrescrito pelo use case
                dto.getCommandNumber(),
                dto.getOpeningDateTime(),
                dto.getClosingDateTime(),
                dto.getDiscount(),
                dto.getTotalValue(),
                client,
                employee,
                false,
                dto.getStatus());
    }

    public static CommandResponseDTO toDTO(Command command) {
        if (command == null) {
            return null;
        }
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
                command.getIsInternal());
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
        if (dto == null)
            return null;
        Client client = null;
        if (dto.getFkClient() != null) {
            client = clientRepository.findById(dto.getFkClient());
        }
        Employee employee = null;
        if (dto.getFkEmployee() != null) {
            employee = employeeRepository.findById(dto.getFkEmployee());
        }
        if (employee == null) {
            throw new RuntimeException("FkEmployee not found");
        }
        return new Command(
                id != null ? id : 0,
                dto.getCommandNumber(),
                dto.getOpeningDateTime(),
                dto.getClosingDateTime(),
                dto.getDiscount(),
                dto.getTotalValue(),
                client,
                employee,
                false,
                dto.getStatus());
    }

    // private Command mapCommonFields(CommandRequestDTO dto, Command command) {
    // if (dto.getFkClient() != null) {
    // Client client = clientRepository.findById(dto.getFkClient()).orElse(null);
    // command.setClient(client);
    // }
    //
    // Employee employee = employeeRepository.findById(dto.getFkEmployee())
    // .orElseThrow(() -> new NotFoundException("FkEmployee not found"));
    //
    // command.setCommandNumber(dto.getCommandNumber());
    // command.setOpeningDateTime(dto.getOpeningDateTime());
    // command.setClosingDateTime(dto.getClosingDateTime());
    // command.setDiscount(dto.getDiscount());
    // command.setTotalValue(dto.getTotalValue());
    // command.setEmployee(employee);
    // command.setStatus(dto.getStatus());
    //
    // return command;
    // }
    //
    // public Command toEntity(CommandRequestDTO dto) {
    // return mapCommonFields(dto, new Command());
    // }
    //
    // public Command toEntity(CommandRequestDTO dto, Integer id) {
    // Command command = new Command();
    // command.setId(id);
    // return mapCommonFields(dto, command);
    // }

}
