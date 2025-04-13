package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.services.SubJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sub-jobs")
@Tag(name = "Sub Jobs", description = "Endpoints for Sub Job Management")
public class SubJobController {

        @Autowired
        private SubJobService subJobService;

        @Operation(
                summary = "Create a new sub job",
                description = "This method is responsible for create a new sub job."
        )
        @PostMapping
        public ResponseEntity<SubJobResponseDTO> createSubJob(
                @RequestBody @Valid SubJobRequestDTO subJobDto
        ) {
                return ResponseEntity.ok(subJobService.save(subJobDto));
        }

        @Operation(
                summary = "Search a sub job",
                description = "This method is responsible for search a sub job."
        )
        @GetMapping("/{id}")
        public ResponseEntity<SubJobResponseDTO> findSubJobById(
                @PathVariable @Valid UUID id
        ) {
                return ResponseEntity.ok(subJobService.findById(id));
        }

        @Operation(
                summary = "List all sub jobs",
                description = "This method is responsible for list all sub jobs."
        )
        @GetMapping
        public ResponseEntity<List<SubJobResponseDTO>> listAllSubJobs() {
                return ResponseEntity.ok(subJobService.findAll());
        }

        @Operation(
                summary = "Update a sub job",
                description = "This method is responsible for update a sub job."
        )
        @PatchMapping("/{id}")
        public ResponseEntity<SubJobResponseDTO> updateSubJob(
                @PathVariable UUID id,
                @RequestBody @Valid SubJobRequestDTO subJobDto
        ) {
                return ResponseEntity.ok(subJobService.update(id, subJobDto));
        }

        @Operation(
                summary = "Delete a sub job",
                description = "This method is responsible for delete a sub job."
        )
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSubJob(
                @PathVariable UUID id
        ) {
                subJobService.delete(id);
                return ResponseEntity.ok().build();
        }
}
