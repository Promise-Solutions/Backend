package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.mappers.ClientMapper;
import com.studiozero.projeto.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Endpoints for Client Management")
public class ClientController {

    private final ClientService clientService;

    @Operation(
            summary = "Create a new client",
            description = "This method is responsible for creating a new client."
    )
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(
            @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        Client client = ClientMapper.toEntity(clientDto);
        clientService.createClient(client);
        return ResponseEntity.status(201).body(ClientMapper.toDTO(client));
    }

    @Operation(
            summary = "Search a client",
            description = "This method is responsible for searching a client."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(
            @PathVariable UUID id
    ) {
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok(ClientMapper.toDTO(client));
    }

    @Operation(
            summary = "List all clients",
            description = "This method is responsible for listing all clients."
    )
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAllClients() {
        List<Client> clients = clientService.listClients();

        if (clients.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<ClientResponseDTO> dtos = ClientMapper.toListDtos(clients);

        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(
            summary = "Update a client",
            description = "This method is responsible for updating a client."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable UUID id,
            @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        Client client = ClientMapper.toEntity(clientDto, id);
        Client updatedClient = clientService.updateClient(client);
        return ResponseEntity.ok(ClientMapper.toDTO(updatedClient));
    }

    @Operation(
            summary = "Delete a client",
            description = "This method is responsible for deleting a client."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable @Valid UUID id
    ) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
