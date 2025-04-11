package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints for Client Management")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(
            summary = "Create a new client",
            description = "This method is responsible for create a new client."
    )
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(
            @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @Operation(
            summary = "Search a client",
            description = "This method is responsible for search a client."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(
            @PathVariable @Valid UUID id
    ) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @Operation(
            summary = "List all clients",
            description = "This method is responsible for list all clients."
    )
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Operation(
            summary = "Update a client",
            description = "This method is responsible for update a client."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable UUID id,
            @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.update(id, clientDto));
    }

    @Operation(
            summary = "Delete a client",
            description = "This method is responsible for delete a client."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(
            @PathVariable @Valid UUID id
    ) {
        return ResponseEntity.ok(clientService.delete(id));
    }
}
