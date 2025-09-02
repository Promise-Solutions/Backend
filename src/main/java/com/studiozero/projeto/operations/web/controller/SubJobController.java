package com.studiozero.projeto.operations.web.controller;

import com.studiozero.projeto.operations.appllication.usecases.job.GetJobUseCase;
import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.domain.entities.SubJob;
import com.studiozero.projeto.operations.web.dto.request.SubJobRequestDTO;
import com.studiozero.projeto.operations.web.dto.request.SubJobUpdateStatusRequestDTO;
import com.studiozero.projeto.operations.web.dto.response.SubJobDeleteResponseDTO;
import com.studiozero.projeto.operations.web.dto.response.SubJobResponseDTO;
import com.studiozero.projeto.operations.web.dto.response.SubJobUpdateStatusResponseDTO;
import com.studiozero.projeto.operations.web.mapper.SubJobMapper;
import com.studiozero.projeto.operations.appllication.usecases.subjob.CreateSubJobUseCase;
import com.studiozero.projeto.operations.appllication.usecases.subjob.GetSubJobUseCase;
import com.studiozero.projeto.operations.appllication.usecases.subjob.UpdateSubJobUseCase;
import com.studiozero.projeto.operations.appllication.usecases.subjob.DeleteSubJobUseCase;
import com.studiozero.projeto.operations.appllication.usecases.subjob.ListSubJobsByFkServiceUseCase;
import com.studiozero.projeto.operations.appllication.usecases.subjob.ListSubJobsUseCase;
import com.studiozero.projeto.operations.appllication.usecases.subjob.UpdateSubJobStatusUseCase;
//import com.studiozero.projeto.application.usecases.job.EvaluateJobStatusUseCase;
//import com.studiozero.projeto.application.usecases.job.CalculateJobTotalValueUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sub-jobs")
@Tag(name = "Sub Jobs", description = "Endpoints for Sub Job Management")
public class SubJobController {

        private final CreateSubJobUseCase createSubJobUseCase;
        private final GetSubJobUseCase getSubJobUseCase;
        private final UpdateSubJobUseCase updateSubJobUseCase;
        private final DeleteSubJobUseCase deleteSubJobUseCase;
        private final ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase;
        private final ListSubJobsUseCase listSubJobsUseCase;
        private final UpdateSubJobStatusUseCase updateSubJobStatusUseCase;
//        private final EvaluateJobStatusUseCase evaluateJobStatusUseCase;
//        private final CalculateJobTotalValueUseCase calculateJobTotalValueUseCase;
        private final GetJobUseCase getJobUseCase;

    public SubJobController(CreateSubJobUseCase createSubJobUseCase,
                            GetSubJobUseCase getSubJobUseCase,
                            UpdateSubJobUseCase updateSubJobUseCase,
                            DeleteSubJobUseCase deleteSubJobUseCase,
                            ListSubJobsByFkServiceUseCase listSubJobsByFkServiceUseCase,
                            ListSubJobsUseCase listSubJobsUseCase,
                            UpdateSubJobStatusUseCase updateSubJobStatusUseCase,
//                            EvaluateJobStatusUseCase evaluateJobStatusUseCase,
//                            CalculateJobTotalValueUseCase calculateJobTotalValueUseCase,
                            GetJobUseCase getJobUseCase) {
        this.createSubJobUseCase = createSubJobUseCase;
        this.getSubJobUseCase = getSubJobUseCase;
        this.updateSubJobUseCase = updateSubJobUseCase;
        this.deleteSubJobUseCase = deleteSubJobUseCase;
        this.listSubJobsByFkServiceUseCase = listSubJobsByFkServiceUseCase;
        this.listSubJobsUseCase = listSubJobsUseCase;
        this.updateSubJobStatusUseCase = updateSubJobStatusUseCase;
//        this.evaluateJobStatusUseCase = evaluateJobStatusUseCase;
//        this.calculateJobTotalValueUseCase = calculateJobTotalValueUseCase;
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
        public ResponseEntity<List<SubJobResponseDTO>> listSubJobsByFkService(
                        @RequestParam @Valid UUID fkService) {
                List<SubJob> subJobs = listSubJobsByFkServiceUseCase.execute(fkService);
                if (subJobs.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                return ResponseEntity.status(200).body(SubJobMapper.toDTOList(subJobs));
        }

        @Operation(summary = "List all sub jobs", description = "This method is responsible for list all sub jobs.")
        @GetMapping
        public ResponseEntity<List<SubJobResponseDTO>> listAllSubJobs() {
                List<SubJob> subJobs = listSubJobsUseCase.execute();
                if (subJobs.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                List<SubJobResponseDTO> subJobDtos = SubJobMapper.toDTOList(subJobs);
                return ResponseEntity.status(200).body(subJobDtos);
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
