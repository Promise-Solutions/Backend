package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.application.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.web.mappers.CommandMapper;
import com.studiozero.projeto.application.services.CommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/commands")
@RequiredArgsConstructor
@Tag(name = "Commands", description = "Endpoints for Command Management")
public class CommandController {

    private final CommandService commandService;
    private final CommandMapper commandMapper;

    @Operation(
            summary = "Create a new command",
            description = "This method is responsible for creating a new command."
    )
    @PostMapping
    public ResponseEntity<CommandResponseDTO> createCommand(
            @RequestBody @Valid CommandRequestDTO commandDto
    ) {
        Command savedCommand = commandService.createCommand(commandDto);
        CommandResponseDTO savedDto = CommandMapper.toDTO(savedCommand);

        return ResponseEntity.status(201).body(savedDto);
    }

    @Operation(
            summary = "Search a command",
            description = "This method is responsible for searching a command."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CommandResponseDTO> findCommandById(
            @PathVariable Integer id
    ) {
        Command command = commandService.findCommandById(id);
        CommandResponseDTO commandDto = CommandMapper.toDTO(command);

        return ResponseEntity.ok(commandDto);
    }

    @Operation(
            summary = "List all commands",
            description = "This method is responsible for listing all commands."
    )
    @GetMapping
    public ResponseEntity<List<CommandResponseDTO>> listAllCommands(@RequestParam Status status) {

        List<Command> commands = new ArrayList();
        if (status == null) {
            commands = commandService.listCommands();
        } else {
            commands = commandService.listCommands(status);
        }

        if (commands.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<CommandResponseDTO> commandDtos = CommandMapper.toListDtos(commands);

        return ResponseEntity.status(200).body(commandDtos);
    }

    @Operation(
            summary = "Update a command",
            description = "This method is responsible for updating a command."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<CommandResponseDTO> updateCommand(
            @PathVariable Integer id,
            @RequestBody @Valid CommandRequestDTO commandDto
    ) {
        Command command = commandMapper.toEntity(commandDto, id);
        Command updatedCommand = commandService.updateCommand(command);
        return ResponseEntity.ok(CommandMapper.toDTO(updatedCommand));
    }

    @Operation(
            summary = "Delete a command",
            description = "This method is responsible for deleting a command."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommand(
            @PathVariable Integer id
    ) {
        commandService.deleteCommand(id);
        return ResponseEntity.ok().build();
    }
}
