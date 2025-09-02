package com.studiozero.projeto.operations.web.controller;

import com.studiozero.projeto.operations.domain.entities.Tracing;
import com.studiozero.projeto.operations.web.dto.request.TracingRequestDTO;
import com.studiozero.projeto.operations.web.dto.response.TracingResponseDTO;
import com.studiozero.projeto.operations.web.mapper.TracingMapper;
import com.studiozero.projeto.operations.appllication.usecases.tracing.CreateTracingUseCase;
import com.studiozero.projeto.operations.appllication.usecases.tracing.ListTracingsUseCase;
import com.studiozero.projeto.operations.appllication.usecases.tracing.DeleteAllTracingsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracing")
@Tag(name = "Tracing", description = "Endpoints for Tracing Management")
public class TracingController {

    private final CreateTracingUseCase createTracingUseCase;
    private final ListTracingsUseCase listTracingsUseCase;
    private final DeleteAllTracingsUseCase deleteAllTracingsUseCase;

    public TracingController(CreateTracingUseCase createTracingUseCase, ListTracingsUseCase listTracingsUseCase, DeleteAllTracingsUseCase deleteAllTracingsUseCase) {
        this.createTracingUseCase = createTracingUseCase;
        this.listTracingsUseCase = listTracingsUseCase;
        this.deleteAllTracingsUseCase = deleteAllTracingsUseCase;
    }

    @PostMapping
    @Operation(summary = "Create Tracing", description = "This method is responsible for create a tracing.")
    public ResponseEntity<TracingResponseDTO> createClient(
            @RequestBody @Valid TracingRequestDTO tracingDto) {
        Tracing tracing = TracingMapper.toDomain(tracingDto);
        Tracing created = createTracingUseCase.execute(tracing);
        return ResponseEntity.status(201).body(TracingMapper.toDTO(created));
    }

    @GetMapping
    @Operation(summary = "List all tracings", description = "This method is responsible for list all tracing.")
    ResponseEntity<List<TracingResponseDTO>> listAllTracings() {
        List<Tracing> tracings = listTracingsUseCase.execute();
        if (tracings.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<TracingResponseDTO> dtos = TracingMapper.toDTOList(tracings);
        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(summary = "Delete all tracing", description = "This method is responsible for deleting all tracing.")
    @DeleteMapping()
    public ResponseEntity<Void> deleteTracing() {
        deleteAllTracingsUseCase.execute();
        return ResponseEntity.ok().build();
    }
}
