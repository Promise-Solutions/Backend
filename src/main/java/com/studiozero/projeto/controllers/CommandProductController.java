package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.services.CommandProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/command-products")
@Tag(name = "Command Products", description = "Endpoints for Command Product Management")
public class CommandProductController {

    @Autowired
    private CommandProductService commandProductService;

    @Operation(
            summary = "Create a command product",
            description = "This method is responsible for create a command product."
    )
    @PostMapping
    public ResponseEntity<CommandProductResponseDTO> createCommandProduct(
            @RequestBody @Valid CommandProductRequestDTO commandProductDto
    ) {
        return ResponseEntity.status(201).body(commandProductService.save(commandProductDto));
    }

    @Operation(
            summary = "Search a command product",
            description = "This method is responsible for search a command product."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> findCommandProductById(
            @PathVariable @Valid Integer id
    ) {
        return ResponseEntity.ok(commandProductService.findById(id));
    }

    @Operation(
            summary = "List all command products",
            description = "This method is responsible for list all command products."
    )
    @GetMapping
    public ResponseEntity<List<CommandProductResponseDTO>> findAllCommandProducts() {
        return ResponseEntity.ok(commandProductService.findAll());
    }

    @Operation(
            summary = "Update a command product",
            description = "This method is responsible for update a command product."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> updateCommandProduct(
            @PathVariable @Valid Integer id,
            @RequestBody @Valid CommandProductRequestDTO commandProductDto
    ) {
        return ResponseEntity.ok(commandProductService.update(id, commandProductDto));
    }

    @Operation(
            summary = "Delete a command product",
            description = "This method is responsible for delete a command product."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandProduct(
            @PathVariable @Valid Integer id
    ) {
        commandProductService.delete(id);
        return ResponseEntity.ok().build();
    }
}
