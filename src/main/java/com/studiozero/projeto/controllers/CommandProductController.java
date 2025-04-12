package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.services.CommandProductService;
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

    @PostMapping
    public ResponseEntity<CommandProductResponseDTO> createCommandProduct(@RequestBody @Valid CommandProductRequestDTO commandProductDto) {
        return ResponseEntity.status(201).body(commandProductService.save(commandProductDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> findById(@PathVariable @Valid Integer id) {
        return ResponseEntity.ok(commandProductService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommandProductResponseDTO>> findAllCommandProducts() {
        return ResponseEntity.ok(commandProductService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommandProductResponseDTO> updateCommandProduct(
            @PathVariable @Valid Integer id,
            @RequestBody @Valid CommandProductRequestDTO commandProductDto) {
        return ResponseEntity.ok(commandProductService.update(id, commandProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommandProduct(@PathVariable @Valid Integer id) {
        return ResponseEntity.ok(commandProductService.delete(id));
    }
}
