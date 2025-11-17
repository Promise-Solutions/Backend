package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.application.usecases.employee.GetEmployeeUseCase;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.entities.CommandEntity;
import com.studiozero.projeto.web.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.web.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.web.mappers.CommandMapper;
import com.studiozero.projeto.application.usecases.command.CreateCommandUseCase;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.ListCommandsUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.command.DeleteCommandUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/commands")
@Tag(name = "Commands", description = "Endpoints for Command Management")
public class CommandController {

    private final CreateCommandUseCase createCommandUseCase;
    private final GetCommandUseCase getCommandUseCase;
    private final ListCommandsUseCase listCommandsUseCase;
    private final UpdateCommandUseCase updateCommandUseCase;
    private final DeleteCommandUseCase deleteCommandUseCase;
    private final GetClientUseCase getClientUseCase;
    private final GetEmployeeUseCase getEmployeeUseCase;

    public CommandController(CreateCommandUseCase createCommandUseCase, GetCommandUseCase getCommandUseCase, ListCommandsUseCase listCommandsUseCase, UpdateCommandUseCase updateCommandUseCase, DeleteCommandUseCase deleteCommandUseCase, GetClientUseCase getClientUseCase, GetEmployeeUseCase getEmployeeUseCase) {
        this.createCommandUseCase = createCommandUseCase;
        this.getCommandUseCase = getCommandUseCase;
        this.listCommandsUseCase = listCommandsUseCase;
        this.updateCommandUseCase = updateCommandUseCase;
        this.deleteCommandUseCase = deleteCommandUseCase;
        this.getClientUseCase = getClientUseCase;
        this.getEmployeeUseCase = getEmployeeUseCase;
    }

    @Operation(summary = "Create a new command", description = "This method is responsible for creating a new command.")
    @PostMapping
    public ResponseEntity<CommandResponseDTO> createCommand(
            @RequestBody @Valid CommandRequestDTO commandDto) {
        Client client = getClientUseCase.execute(commandDto.getFkClient());
        Employee employee = getEmployeeUseCase.execute(commandDto.getFkEmployee());
        Command command = CommandMapper.toDomain(commandDto, client, employee);
        Command savedCommand = createCommandUseCase.execute(command);
        CommandResponseDTO savedDto = CommandMapper.toDTO(savedCommand);
        return ResponseEntity.status(201).body(savedDto);
    }

    @Operation(summary = "Search a command", description = "This method is responsible for searching a command.")
    @GetMapping("/{id}")
    public ResponseEntity<CommandResponseDTO> findCommandById(
            @PathVariable Integer id) {
        Command command = getCommandUseCase.execute(id);
        CommandResponseDTO commandDto = CommandMapper.toDTO(command);
        return ResponseEntity.ok(commandDto);
    }

    @Operation(summary = "List all commands", description = "This method is responsible for listing all commands.")
    @GetMapping
    public ResponseEntity<List<CommandResponseDTO>> listAllCommands(
            @RequestParam(required = false) Status status
    ) {
        List<Command> commands;
        if (status != null) {
            commands = listCommandsUseCase.execute(status);
        } else {
            commands = listCommandsUseCase.execute();
        }


        if (commands.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CommandResponseDTO> dtoPage = commands.stream().map(CommandMapper::toDTO).toList();

        return ResponseEntity.ok(dtoPage);
    }

    @Operation(summary = "Update a command", description = "This method is responsible for updating a command.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommandResponseDTO> updateCommand(
            @PathVariable Integer id,
            @RequestBody @Valid CommandRequestDTO commandDto) {
        Client client = getClientUseCase.execute(commandDto.getFkClient());
        Employee employee = getEmployeeUseCase.execute(commandDto.getFkEmployee());
        Command command = CommandMapper.toDomain(commandDto, id, client, employee);
        Command updatedCommand = updateCommandUseCase.execute(command);
        return ResponseEntity.ok(CommandMapper.toDTO(updatedCommand));
    }

    @Operation(summary = "Delete a command", description = "This method is responsible for deleting a command.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommand(
            @PathVariable Integer id) {
        deleteCommandUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
