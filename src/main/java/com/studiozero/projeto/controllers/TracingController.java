package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.TracingRequestDTO;
import com.studiozero.projeto.dtos.response.TracingResponseDTO;
import com.studiozero.projeto.entities.Tracing;
import com.studiozero.projeto.mappers.TracingMapper;
import com.studiozero.projeto.services.TracingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tracing")
@Tag(name = "Tracing", description = "Endpoints for Tracing Management")
public class TracingController {

    private final TracingService tracingService;

    @PostMapping
    @Operation(
            summary = "Create Tracing",
            description = "This method is responsible for create a tracing."
    )
    public ResponseEntity<TracingResponseDTO> createClient(
            @RequestBody @Valid TracingRequestDTO tracingDto
    ) {
        Tracing tracing = TracingMapper.toEntity(tracingDto);
        tracingService.createTracing(tracing);
        return ResponseEntity.status(201).body(TracingMapper.toDTO(tracing));
    }

    @GetMapping
    @Operation(
            summary = "List all tracings",
            description = "This method is responsible for list all tracing."
    )
    ResponseEntity<List<TracingResponseDTO>> listAllTracings() {
        List<Tracing> tracings = tracingService.listTracings();

        if (tracings.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<TracingResponseDTO> dtos = TracingMapper.toListDtos(tracings);

        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(
            summary = "Delete all tracing",
            description = "This method is responsible for deleting all tracing."
    )
    @DeleteMapping()
    public ResponseEntity<Void> deleteTracing(
    ) {
        tracingService.deleteAllTracings();
        return ResponseEntity.ok().build();
    }
}
