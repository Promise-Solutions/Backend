package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ClientRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.services.ClientService;
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

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(
            @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(
            @PathVariable @Valid UUID id
    ) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable UUID id,
            @RequestBody @Valid ClientRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.update(id, clientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(
            @PathVariable @Valid UUID id
    ) {
        return ResponseEntity.ok(clientService.delete(id));
    }
}
