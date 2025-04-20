package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.entities.CommandProduct;
import com.studiozero.projeto.mappers.CommandProductMapper;
import com.studiozero.projeto.services.CommandProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/command-products")
@RequiredArgsConstructor
@Tag(name = "Command Products", description = "Endpoints for Command Product Management")
public class CommandProductController {

    private final CommandProductService commandProductService;
    private final CommandProductMapper commandProductMapper;

    @Operation(
            summary = "Create a command product",
            description = "This method is responsible for create a command product."
    )
    @PostMapping
    public ResponseEntity<CommandProductResponseDTO> createCommandProduct(
            @RequestBody @Valid CommandProductRequestDTO commandProductDto
    ) {
        CommandProduct savedCommandProduct = commandProductService.createCommandProduct(commandProductDto);

        CommandProductResponseDTO savedDto = CommandProductMapper.toDTO(savedCommandProduct);

        return ResponseEntity.status(201).body(savedDto);
    }

    @Operation(
            summary = "Search a command product",
            description = "This method is responsible for search a command product."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> findCommandProductById(
            @PathVariable @Valid Integer id
    ) {
        CommandProduct commandProduct = commandProductService.findCommandProductById(id);

        CommandProductResponseDTO commandProductDto = CommandProductMapper.toDTO(commandProduct);

        return ResponseEntity.ok(commandProductDto);
    }

    @Operation(
            summary = "List all command products",
            description = "This method is responsible for list all command products."
    )
    @GetMapping
    public ResponseEntity<List<CommandProductResponseDTO>> findAllCommandProducts(@RequestParam Integer fkComanda) {
        List<CommandProduct> commandProducts =new ArrayList();

        if (fkComanda == null) {
            commandProducts = commandProductService.listCommandProducts();

        } else {
            commandProducts = commandProductService.listCommandProducts(fkComanda);
        }

        if (commandProducts.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<CommandProductResponseDTO> commandProductsDtos = CommandProductMapper.toListDtos(commandProducts);

        return ResponseEntity.status(200).body(commandProductsDtos);
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
        CommandProduct commandProduct = commandProductMapper.toEntity(commandProductDto, id);

        CommandProduct updatedCommandProduct = commandProductService.updateCommandProduct(commandProduct);

        return ResponseEntity.ok(CommandProductMapper.toDTO(updatedCommandProduct));
    }

    @Operation(
            summary = "Delete a command product",
            description = "This method is responsible for delete a command product."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandProduct(
            @PathVariable @Valid Integer id
    ) {
        commandProductService.deleteCommandProduct(id);
        return ResponseEntity.ok().build();
    }
}
