package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.services.CommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commands")
@Tag(name = "Commands", description = "Endpoints for Command Management")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @Operation(summary = "Create a new command", description = "This method is responsible for creating a new command.")
    @PostMapping
    public ResponseEntity<CommandResponseDTO> createCommand(@RequestBody @Valid CommandRequestDTO commandDto) {
        return ResponseEntity.status(201).body(commandService.save(commandDto));
    }

    @Operation(summary = "Search a command", description = "This method is responsible for searching a command.")
    @GetMapping("/{id}")
    public ResponseEntity<CommandResponseDTO> findCommandById(@PathVariable Integer id) {
        return ResponseEntity.ok(commandService.findById(id));
    }

    @Operation(summary = "List all commands", description = "This method is responsible for listing all commands.")
    @GetMapping
    public ResponseEntity<List<CommandResponseDTO>> listAllCommands() {
        return ResponseEntity.ok(commandService.findAll());
    }

    @Operation(summary = "Update a command", description = "This method is responsible for updating a command.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommandResponseDTO> updateCommand(@PathVariable Integer id,
            @RequestBody @Valid CommandRequestDTO commandDto) {
        return ResponseEntity.ok(commandService.update(id, commandDto));
    }

    @Operation(summary = "Delete a command", description = "This method is responsible for deleting a command.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommand(@PathVariable Integer id) {
        commandService.delete(id);
        return ResponseEntity.ok().build();
    }
}
