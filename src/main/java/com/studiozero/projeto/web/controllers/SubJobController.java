package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.job.GetJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.*;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.web.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.web.dtos.request.SubJobUpdateStatusRequestDTO;
import com.studiozero.projeto.web.dtos.response.SubJobDeleteResponseDTO;
import com.studiozero.projeto.web.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.web.dtos.response.SubJobUpdateStatusResponseDTO;
import com.studiozero.projeto.web.mappers.SubJobMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sub-jobs")
@Tag(name = "Sub Jobs", description = "Endpoints for Sub Job Management")
public class SubJobController {

        private final CreateSubJobUseCase createSubJobUseCase;
        private final GetSubJobUseCase getSubJobUseCase;
        private final UpdateSubJobUseCase updateSubJobUseCase;
        private final DeleteSubJobUseCase deleteSubJobUseCase;
        private final ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase;
        private final ListSubJobsUseCase listSubJobsUseCase;
        private final UpdateSubJobStatusUseCase updateSubJobStatusUseCase;
        private final GetJobUseCase getJobUseCase;

    public SubJobController(CreateSubJobUseCase createSubJobUseCase,
                            GetSubJobUseCase getSubJobUseCase,
                            UpdateSubJobUseCase updateSubJobUseCase,
                            DeleteSubJobUseCase deleteSubJobUseCase,
                            ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase,
                            ListSubJobsUseCase listSubJobsUseCase,
                            UpdateSubJobStatusUseCase updateSubJobStatusUseCase,
                            GetJobUseCase getJobUseCase) {
        this.createSubJobUseCase = createSubJobUseCase;
        this.getSubJobUseCase = getSubJobUseCase;
        this.updateSubJobUseCase = updateSubJobUseCase;
        this.deleteSubJobUseCase = deleteSubJobUseCase;
        this.listSubJobsByFkServiceUseCase = listSubJobsByFkServiceUseCase;
        this.listSubJobsUseCase = listSubJobsUseCase;
        this.updateSubJobStatusUseCase = updateSubJobStatusUseCase;
        this.getJobUseCase = getJobUseCase;
    }

    @Operation(summary = "Create a new sub job", description = "This method is responsible for create a new sub job.")
        @PostMapping
        public ResponseEntity<SubJobResponseDTO> createSubJob(
                        @RequestBody @Valid SubJobRequestDTO subJobDto) {

            Job job = getJobUseCase.execute(subJobDto.getFkService());
            SubJob subJob = SubJobMapper.toDomain(subJobDto, job);
            SubJob savedSubJob = createSubJobUseCase.execute(subJob);
            Job jobChanged = savedSubJob.getJob();
            return ResponseEntity.status(201).body(SubJobMapper.toDTO(savedSubJob, jobChanged));
        }

        @Operation(summary = "Search a sub job", description = "This method is responsible for search a sub job.")
        @GetMapping("/{id}")
        public ResponseEntity<SubJobResponseDTO> findSubJobById(
                        @PathVariable @Valid UUID id) {
                SubJob subJob = getSubJobUseCase.execute(id);
                SubJobResponseDTO jobDto = SubJobMapper.toDTO(subJob);
                return ResponseEntity.ok(jobDto);
        }

        @Operation(summary = "List subJobs By a fkService", description = "This method is responsible for listing all subJobs associated with a job.")
        @GetMapping("/job")
        public ResponseEntity<Page<SubJobResponseDTO>> listSubJobsByFkService(
                        @RequestParam @Valid UUID fkService,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size
        ) {
                Page<SubJob> subJobs = listSubJobsByFkServiceUseCase.execute(fkService, PageRequest.of(page, size));
                if (subJobs.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
            Page<SubJobResponseDTO> subJobDtos = subJobs.map(SubJobMapper::toDTO);
            return ResponseEntity.ok(subJobDtos);
        }

    @Operation(summary = "List all sub jobs", description = "This method is responsible for list all sub jobs.")
    @GetMapping
    public ResponseEntity<List<SubJobResponseDTO>> listAllSubJobs() {
        List<SubJob> subJobs = listSubJobsUseCase.execute();
        if (subJobs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<SubJobResponseDTO> subJobDtos = subJobs.stream().map(SubJobMapper::toDTO).toList();
        return ResponseEntity.ok(subJobDtos);
    }

        @Operation(summary = "Update a sub job", description = "This method is responsible for update a sub job.")
        @PatchMapping("/{id}")
        public ResponseEntity<SubJobResponseDTO> updateSubJob(
                        @PathVariable UUID id,
                        @RequestBody @Valid SubJobRequestDTO subJobDto) {
                Job job = getJobUseCase.execute(subJobDto.getFkService());
                SubJob subJob = SubJobMapper.toDomain(subJobDto, id, job);
                SubJob updatedSubJob = updateSubJobUseCase.execute(subJob);

                Double jobTotalValue = updatedSubJob.getJob().getTotalValue();
                return ResponseEntity.ok(SubJobMapper.toDTO(updatedSubJob, jobTotalValue));
        }

        @Operation(summary = "Update a sub job status", description = "This method is responsible for update a sub job status")
        @PatchMapping("/{id}/status")
        public ResponseEntity<SubJobUpdateStatusResponseDTO> updateSubJobStatus(
                        @PathVariable UUID id,
                        @RequestBody @Valid SubJobUpdateStatusRequestDTO statusDTO) {
                SubJob subJobUpdated = updateSubJobStatusUseCase.execute(id, statusDTO.getStatus(), statusDTO.getJobId());

                return ResponseEntity.ok().body(new SubJobUpdateStatusResponseDTO(subJobUpdated.getId(),
                                subJobUpdated.getStatus(), subJobUpdated.getJob().getStatus()));
        }

        @Operation(summary = "Delete a sub job", description = "This method is responsible for delete a sub job.")
        @DeleteMapping("/{id}")
        public ResponseEntity<SubJobDeleteResponseDTO> deleteSubJob(
                        @PathVariable UUID id) {
            SubJob subJobDeleted = deleteSubJobUseCase.execute(id);

            return ResponseEntity.ok().body(SubJobMapper.toDTO(id, subJobDeleted.getJob()));
        }
}
