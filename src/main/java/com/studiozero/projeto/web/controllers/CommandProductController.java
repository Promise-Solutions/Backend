package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.web.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.web.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.web.mappers.CommandProductMapper;
import com.studiozero.projeto.application.usecases.commandproduct.CreateCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.GetCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.UpdateCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.DeleteCommandProductUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.ListCommandProductsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/command-products")
@Tag(name = "Command Products", description = "Endpoints for Command Product Management")
public class CommandProductController {

    private final CreateCommandProductUseCase createCommandProductUseCase;
    private final GetCommandProductUseCase getCommandProductUseCase;
    private final UpdateCommandProductUseCase updateCommandProductUseCase;
    private final DeleteCommandProductUseCase deleteCommandProductUseCase;
    private final ListCommandProductsUseCase listCommandProductsUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetCommandUseCase getCommandUseCase;

    public CommandProductController(CreateCommandProductUseCase createCommandProductUseCase, GetCommandProductUseCase getCommandProductUseCase, UpdateCommandProductUseCase updateCommandProductUseCase, DeleteCommandProductUseCase deleteCommandProductUseCase, ListCommandProductsUseCase listCommandProductsUseCase, GetProductUseCase getProductUseCase, GetCommandUseCase getCommandUseCase) {
        this.createCommandProductUseCase = createCommandProductUseCase;
        this.getCommandProductUseCase = getCommandProductUseCase;
        this.updateCommandProductUseCase = updateCommandProductUseCase;
        this.deleteCommandProductUseCase = deleteCommandProductUseCase;
        this.listCommandProductsUseCase = listCommandProductsUseCase;
        this.getProductUseCase = getProductUseCase;
        this.getCommandUseCase = getCommandUseCase;
    }

    @Operation(summary = "Create a command product", description = "This method is responsible for create a command product.")
    @PostMapping
    public ResponseEntity<CommandProductResponseDTO> createCommandProduct(
            @RequestBody @Valid CommandProductRequestDTO commandProductDto) {
        Product product = getProductUseCase.execute(commandProductDto.getFkProduct());
        Command command = getCommandUseCase.execute(commandProductDto.getFkCommand());
        CommandProduct commandProduct = CommandProductMapper.toDomain(commandProductDto,product, command);
        CommandProduct savedCommandProduct = createCommandProductUseCase.execute(commandProduct);
        CommandProductResponseDTO savedDto = CommandProductMapper.toDTO(savedCommandProduct);
        return ResponseEntity.status(201).body(savedDto);
    }

    @Operation(summary = "Search a command product", description = "This method is responsible for search a command product.")
    @GetMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> findCommandProductById(
            @PathVariable @Valid Integer id) {
        CommandProduct commandProduct = getCommandProductUseCase.execute(id);
        CommandProductResponseDTO commandProductDto = CommandProductMapper.toDTO(commandProduct);
        return ResponseEntity.ok(commandProductDto);
    }

    @Operation(summary = "List all command products", description = "This method is responsible for list all command products.")
    @GetMapping
    public ResponseEntity<List<CommandProductResponseDTO>> findAllCommandProducts(
            @RequestParam(required = false) Integer fkComanda) {
        List<CommandProduct> commandProducts = listCommandProductsUseCase.execute(fkComanda);
        if (commandProducts.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CommandProductResponseDTO> commandProductsDtos = CommandProductMapper.toDTOList(commandProducts);
        return ResponseEntity.status(200).body(commandProductsDtos);
    }

    @Operation(summary = "Update a command product", description = "This method is responsible for update a command product.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> updateCommandProduct(
            @PathVariable @Valid Integer id,
            @RequestBody @Valid CommandProductRequestDTO commandProductDto) {

        Product product = getProductUseCase.execute(commandProductDto.getFkProduct());
        Command command = getCommandUseCase.execute(commandProductDto.getFkCommand());
        CommandProduct commandProduct = CommandProductMapper.toDomain(commandProductDto, id, product, command);

        CommandProduct updatedCommandProduct = updateCommandProductUseCase.execute(commandProduct);
        return ResponseEntity.ok(CommandProductMapper.toDTO(updatedCommandProduct));
    }

    @Operation(summary = "Delete a command product", description = "This method is responsible for delete a command product.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandProduct(
            @PathVariable @Valid Integer id) {
        deleteCommandProductUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
