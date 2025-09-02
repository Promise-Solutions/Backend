package com.studiozero.projeto.sales.web.controller;

import com.studiozero.projeto.customers.application.client.GetClientUseCase;
import com.studiozero.projeto.users.application.usecases.GetEmployeeUseCase;
import com.studiozero.projeto.customers.domain.Client;
import com.studiozero.projeto.sales.domain.entities.Command;
import com.studiozero.projeto.shared.application.enums.Status;
import com.studiozero.projeto.users.domain.entities.Employee;
import com.studiozero.projeto.sales.web.dto.request.CommandRequestDTO;
import com.studiozero.projeto.sales.web.dto.response.CommandResponseDTO;
import com.studiozero.projeto.sales.web.mapper.CommandMapper;
import com.studiozero.projeto.sales.applications.command.CreateCommandUseCase;
import com.studiozero.projeto.sales.applications.command.GetCommandUseCase;
import com.studiozero.projeto.sales.applications.command.ListCommandsUseCase;
import com.studiozero.projeto.sales.applications.command.UpdateCommandUseCase;
import com.studiozero.projeto.sales.applications.command.DeleteCommandUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commands")
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
    public ResponseEntity<List<CommandResponseDTO>> listAllCommands(@RequestParam(required = false) Status status) {
        List<Command> commands;
        if (status == null) {
            commands = listCommandsUseCase.execute();
        } else {
            commands = listCommandsUseCase.execute(status);
        }
        if (commands.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CommandResponseDTO> commandDtos = CommandMapper.toDTOList(commands);
        return ResponseEntity.status(200).body(commandDtos);
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
