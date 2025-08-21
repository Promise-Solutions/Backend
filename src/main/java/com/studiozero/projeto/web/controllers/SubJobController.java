package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.web.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.web.dtos.request.SubJobUpdateStatusRequestDTO;
import com.studiozero.projeto.web.dtos.response.SubJobDeleteResponseDTO;
import com.studiozero.projeto.web.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.web.dtos.response.SubJobUpdateStatusResponseDTO;
import com.studiozero.projeto.web.mappers.SubJobMapper;
import com.studiozero.projeto.application.usecases.subjob.CreateSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.GetSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.UpdateSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.DeleteSubJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.ListSubJobsByFkServiceUseCase;
import com.studiozero.projeto.application.usecases.subjob.ListSubJobsUseCase;
import com.studiozero.projeto.application.usecases.subjob.UpdateSubJobStatusUseCase;
import com.studiozero.projeto.application.usecases.job.EvaluateJobStatusUseCase;
import com.studiozero.projeto.application.usecases.job.CalculateJobTotalValueUseCase;
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

        private final CreateSubJobUseCase createSubJobUseCase;
        private final GetSubJobUseCase getSubJobUseCase;
        private final UpdateSubJobUseCase updateSubJobUseCase;
        private final DeleteSubJobUseCase deleteSubJobUseCase;
        private final ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase;
        private final ListSubJobsUseCase listSubJobsUseCase;
        private final UpdateSubJobStatusUseCase updateSubJobStatusUseCase;
        private final EvaluateJobStatusUseCase evaluateJobStatusUseCase;
        private final CalculateJobTotalValueUseCase calculateJobTotalValueUseCase;
        private final SubJobMapper subJobMapper;

        @Operation(summary = "Create a new sub job", description = "This method is responsible for create a new sub job.")
        @PostMapping
        public ResponseEntity<SubJobResponseDTO> createSubJob(
                        @RequestBody @Valid SubJobRequestDTO subJobDto) {
                SubJob subJob = subJobMapper.toEntity(subJobDto);
                SubJob savedSubJob = createSubJobUseCase.execute(subJob);
                Status jobStatus = evaluateJobStatusUseCase.execute(savedSubJob.getJob().getId());
                Double totalValueJob = calculateJobTotalValueUseCase.execute(savedSubJob.getJob());
                return ResponseEntity.status(201).body(SubJobMapper.toDTO(savedSubJob, jobStatus, totalValueJob));
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
        public ResponseEntity<List<SubJobResponseDTO>> listSubJobsByFkService(
                        @RequestParam @Valid UUID fkService) {
                List<SubJob> subJobs = listSubJobsByFkServiceUseCase.execute(fkService);
                if (subJobs.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                return ResponseEntity.status(200).body(SubJobMapper.toListDtos(subJobs));
        }

        @Operation(summary = "List all sub jobs", description = "This method is responsible for list all sub jobs.")
        @GetMapping
        public ResponseEntity<List<SubJobResponseDTO>> listAllSubJobs() {
                List<SubJob> subJobs = listSubJobsUseCase.execute();
                if (subJobs.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                List<SubJobResponseDTO> subJobDtos = SubJobMapper.toListDtos(subJobs);
                return ResponseEntity.status(200).body(subJobDtos);
        }

        @Operation(summary = "Update a sub job", description = "This method is responsible for update a sub job.")
        @PatchMapping("/{id}")
        public ResponseEntity<SubJobResponseDTO> updateSubJob(
                        @PathVariable UUID id,
                        @RequestBody @Valid SubJobRequestDTO subJobDto) {
                SubJob subJob = subJobMapper.toEntity(subJobDto, id);
                SubJob updatedSubJob = updateSubJobUseCase.execute(subJob);
                Double totalValueJob = calculateJobTotalValueUseCase.execute(updatedSubJob.getJob());
                return ResponseEntity.ok(SubJobMapper.toDTO(updatedSubJob, totalValueJob));
        }

        @Operation(summary = "Update a sub job status", description = "This method is responsible for update a sub job status")
        @PatchMapping("/{id}/status")
        public ResponseEntity<SubJobUpdateStatusResponseDTO> updateSubJobStatus(
                        @PathVariable UUID id,
                        @RequestBody @Valid SubJobUpdateStatusRequestDTO statusDTO) {
                SubJob subJobUpdated = updateSubJobStatusUseCase.execute(id, statusDTO.getStatus());
                Status jobStatus = evaluateJobStatusUseCase.execute(subJobUpdated.getJob().getId());
                return ResponseEntity.ok().body(new SubJobUpdateStatusResponseDTO(subJobUpdated.getId(),
                                subJobUpdated.getStatus(), jobStatus));
        }

        @Operation(summary = "Delete a sub job", description = "This method is responsible for delete a sub job.")
        @DeleteMapping("/{id}")
        public ResponseEntity<SubJobDeleteResponseDTO> deleteSubJob(
                        @PathVariable UUID id) {
                deleteSubJobUseCase.execute(id);
                return ResponseEntity.ok().build();
        }
}
