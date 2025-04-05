package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.applications.web.dtos.request.ClientCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.domain.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping("/save")
    public ResponseEntity<ClientResponseDTO> saveClient(
            @RequestBody ClientCreateRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientResponseDTO>> listClients() {
        return ResponseEntity.ok(clientService.list());
    }

    @PatchMapping("/update")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @RequestBody ClientUpdateRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.update(clientDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClient(
            @RequestBody ClientDeleteRequestDTO clientDto
    ) {
        return ResponseEntity.ok(clientService.delete(clientDto));
    }

}
