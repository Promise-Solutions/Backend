package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.web.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.web.mappers.ClientMapper;
import com.studiozero.projeto.application.usecases.client.CreateClientUseCase;
import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.application.usecases.client.ListClientsUseCase;
import com.studiozero.projeto.application.usecases.client.UpdateClientUseCase;
import com.studiozero.projeto.application.usecases.client.DeleteClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Endpoints for Client Management")
public class ClientController {

     private final CreateClientUseCase createClientUseCase;
     private final GetClientUseCase getClientUseCase;
     private final ListClientsUseCase listClientsUseCase;
     private final UpdateClientUseCase updateClientUseCase;
     private final DeleteClientUseCase deleteClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase, GetClientUseCase getClientUseCase, ListClientsUseCase listClientsUseCase, UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.getClientUseCase = getClientUseCase;
        this.listClientsUseCase = listClientsUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }

    @Operation(summary = "Create a new client", description = "This method is responsible for creating a new client.")
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(
        @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        Client client = ClientMapper.toDomain(clientDto);
        Client created = createClientUseCase.execute(client);
        return ResponseEntity.status(201).body(ClientMapper.toDTO(created));
    }

    @Operation(summary = "Search a client", description = "This method is responsible for searching a client.")
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(
        @PathVariable UUID id
    ) {
        Client client = getClientUseCase.execute(id);
        return ResponseEntity.ok(ClientMapper.toDTO(client));
    }

    @Operation(summary = "List all clients", description = "This method is responsible for listing all clients.")
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAllClients() {
        List<Client> clients = listClientsUseCase.execute();
        if (clients.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<ClientResponseDTO> dtos = ClientMapper.toDTOList(clients);
        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(summary = "Update a client", description = "This method is responsible for updating a client.")
    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
        @PathVariable UUID id,
        @RequestBody @Valid ClientUpdateRequestDTO clientDto
    ) {
        Client client = ClientMapper.toDomain(clientDto, id);
        Client updatedClient = updateClientUseCase.execute(client);
        return ResponseEntity.ok(ClientMapper.toDTO(updatedClient));
    }

    @Operation(summary = "Delete a client", description = "This method is responsible for deleting a client.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
        @PathVariable @Valid UUID id
    ) {
        deleteClientUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
