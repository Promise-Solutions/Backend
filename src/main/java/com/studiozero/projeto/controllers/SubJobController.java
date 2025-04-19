package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.mappers.ClientMapper;
import com.studiozero.projeto.mappers.SubJobMapper;
import com.studiozero.projeto.services.SubJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sub-jobs")
@RequiredArgsConstructor
@Tag(name = "Sub Jobs", description = "Endpoints for Sub Job Management")
public class SubJobController {

    private final SubJobService subJobService;
    private final SubJobMapper subJobMapper;

    @Operation(
            summary = "Create a new sub job",
            description = "This method is responsible for create a new sub job."
    )
    @PostMapping
    public ResponseEntity<SubJobResponseDTO> createSubJob(
            @RequestBody @Valid SubJobRequestDTO subJobDto
    ) {
        SubJob savedSubJob = subJobService.createSubJob(subJobDto);
        SubJobResponseDTO savedSubJobDto = SubJobMapper.toDTO(savedSubJob);
        return ResponseEntity.status(201).body(savedSubJobDto);
    }

    @Operation(
            summary = "Search a sub job",
            description = "This method is responsible for search a sub job."
    )
    @GetMapping("/{id}")
    public ResponseEntity<SubJobResponseDTO> findSubJobById(
            @PathVariable @Valid UUID id
    ) {
        SubJob subJob = subJobService.findSubJobById(id);
        SubJobResponseDTO jobDto = SubJobMapper.toDTO(subJob);
        return ResponseEntity.ok(jobDto);
    }

    @Operation(
            summary = "List all sub jobs",
            description = "This method is responsible for list all sub jobs."
    )
    @GetMapping
    public ResponseEntity<List<SubJobResponseDTO>> listAllSubJobs() {
        List<SubJob> subJobs = subJobService.listSubJobs();

        if (subJobs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<SubJobResponseDTO> subJobDtos = SubJobMapper.toListDtos(subJobs);
        return ResponseEntity.status(200).body(subJobDtos);
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
        SubJob subjob = subJobMapper.toEntity(subJobDto, id);
        SubJob updatedSubJob = subJobService.updateSubJob(subjob);

        return ResponseEntity.ok(SubJobMapper.toDTO(updatedSubJob));
    }

    @Operation(
            summary = "Delete a sub job",
            description = "This method is responsible for delete a sub job."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubJob(
            @PathVariable UUID id
    ) {
        subJobService.deleteSubJob(id);
        return ResponseEntity.ok().build();
    }
}
