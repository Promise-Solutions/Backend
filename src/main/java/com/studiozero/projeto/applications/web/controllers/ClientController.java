package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.applications.web.dtos.request.ClientCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientSearchRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.domain.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints for Client Management")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> saveClient(
            @RequestBody @Valid ClientCreateRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @GetMapping("/seach")
    public ResponseEntity<ClientResponseDTO> searchClient(
            @PathVariable @Valid ClientSearchRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.search(clientDto));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listClients() {
        return ResponseEntity.ok(clientService.list());
    }

    @PatchMapping
    public ResponseEntity<ClientResponseDTO> updateClient(
            @RequestBody @Valid ClientUpdateRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.update(clientDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteClient(
            @RequestBody @Valid ClientDeleteRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.delete(clientDto));
    }
}
