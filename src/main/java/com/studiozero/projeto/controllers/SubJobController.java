package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.request.SubJobUpdateStatusRequestDTO;
import com.studiozero.projeto.dtos.response.*;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.mappers.SubJobMapper;
import com.studiozero.projeto.services.JobService;
import com.studiozero.projeto.services.SubJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final JobService jobService;

    @Operation(
            summary = "Create a new sub job",
            description = "This method is responsible for create a new sub job."
    )
    @PostMapping
    public ResponseEntity<SubJobResponseDTO> createSubJob(
            @RequestBody @Valid SubJobRequestDTO subJobDto
    ) {
        SubJob subJob = subJobMapper.toEntity(subJobDto);
        SubJob savedSubJob = subJobService.createSubJob(subJob);

        Status jobStatus = jobService.evaluateJobStatus(savedSubJob.getJob().getId());
        Double totalValueJob = jobService.calculateTotalValue(savedSubJob.getJob().getId());

        return ResponseEntity.status(201).body(SubJobMapper.toDTO(savedSubJob, jobStatus, totalValueJob));
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
            summary = "List subJobs By a fkService",
            description = "This method is responsible for listing all subJobs associated with a job."
    )
    @GetMapping("/job")
    public ResponseEntity<List<SubJobResponseDTO>> listSubJobsByFkService(
            @RequestParam @Valid UUID fkService
    ) {
        List<SubJob> subJobs = subJobService.listSubJobsByFkService(fkService);
        if(subJobs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(SubJobMapper.toListDtos(subJobs));
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
        SubJob subJob = subJobMapper.toEntity(subJobDto, id);
        SubJob updatedSubJob = subJobService.updateSubJob(subJob);

        Double totalValueJob = jobService.calculateTotalValue(updatedSubJob.getJob().getId());

        return ResponseEntity.ok(SubJobMapper.toDTO(updatedSubJob, totalValueJob));
    }

    @Operation(
            summary = "Update a sub job status",
            description = "This method is responsible for update a sub job status"
    )
    @PatchMapping("/{id}/update-status")
    public ResponseEntity<SubJobUpdateStatusResponseDTO> updateSubJobStatus(
        @PathVariable UUID id,
        @RequestBody @Valid SubJobUpdateStatusRequestDTO statusDTO
    ) {
        SubJob subJobUpdated = subJobService.updateSubJobStatus(id, statusDTO.getStatus());
        Status jobStatus = jobService.evaluateJobStatus(subJobUpdated.getJob().getId());
        return ResponseEntity.ok().body(new SubJobUpdateStatusResponseDTO(subJobUpdated.getId(), subJobUpdated.getStatus(), jobStatus));
    }

    @Operation(
            summary = "Delete a sub job",
            description = "This method is responsible for delete a sub job."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<SubJobDeleteResponseDTO> deleteSubJob(
            @PathVariable UUID id
    ) {
        SubJobDeleteResponseDTO dtoResponse = subJobService.deleteSubJob(id);
        return ResponseEntity.ok().body(dtoResponse);
    }
}
