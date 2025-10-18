package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.web.dtos.request.JobRequestDTO;
import com.studiozero.projeto.web.dtos.response.JobResponseDTO;
import com.studiozero.projeto.web.mappers.JobMapper;
import com.studiozero.projeto.application.usecases.job.CreateJobUseCase;
import com.studiozero.projeto.application.usecases.job.GetJobUseCase;
import com.studiozero.projeto.application.usecases.job.UpdateJobUseCase;
import com.studiozero.projeto.application.usecases.job.DeleteJobUseCase;
import com.studiozero.projeto.application.usecases.job.ListJobsUseCase;
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
@RequestMapping("/jobs")
@Tag(name = "Jobs", description = "Endpoints for Job Management")
public class JobController {

        private final CreateJobUseCase createJobUseCase;
        private final GetJobUseCase getJobUseCase;
        private final UpdateJobUseCase updateJobUseCase;
        private final DeleteJobUseCase deleteJobUseCase;
        private final ListJobsUseCase listJobsUseCase;
        private final GetClientUseCase getClientUseCase;

    public JobController(CreateJobUseCase createJobUseCase, GetJobUseCase getJobUseCase, UpdateJobUseCase updateJobUseCase, DeleteJobUseCase deleteJobUseCase, ListJobsUseCase listJobsUseCase, GetClientUseCase getClientUseCase) {
        this.createJobUseCase = createJobUseCase;
        this.getJobUseCase = getJobUseCase;
        this.updateJobUseCase = updateJobUseCase;
        this.deleteJobUseCase = deleteJobUseCase;
        this.listJobsUseCase = listJobsUseCase;
        this.getClientUseCase = getClientUseCase;
    }

    @Operation(summary = "Create a new job", description = "This method is responsible for creating a new job.")
        @PostMapping
        public ResponseEntity<JobResponseDTO> createJob(
                        @RequestBody @Valid JobRequestDTO jobDto) {
            Client client = getClientUseCase.execute(jobDto.getFkClient());
                Job job = JobMapper.toDomain(jobDto, client);
                createJobUseCase.execute(job);
                JobResponseDTO savedDto = JobMapper.toDTO(job);
                return ResponseEntity.status(201).body(savedDto);
        }

        @Operation(summary = "Search a job", description = "This method is responsible for searching a job.")
        @GetMapping("/{id}")
        public ResponseEntity<JobResponseDTO> findJobById(
                        @PathVariable @Valid UUID id) {
                Job job = getJobUseCase.execute(id);
                JobResponseDTO jobDto = JobMapper.toDTO(job);
                return ResponseEntity.ok(jobDto);
        }

    @Operation(summary = "List all jobs", description = "This method is responsible for listing all jobs.")
    @GetMapping
    public ResponseEntity<Page<JobResponseDTO>> listAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Job> jobPage = listJobsUseCase.execute(PageRequest.of(page, size));
        if (jobPage.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        Page<JobResponseDTO> dtoPage = jobPage.map(JobMapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

        @Operation(summary = "List jobs By a fkClient", description = "This method is responsible for listing all jobs associated with a client.")
        @GetMapping("/client")
        public ResponseEntity<List<JobResponseDTO>> listJobsByFkClient(
                        @RequestParam @Valid UUID fkClient) {
                List<Job> jobs = listJobsUseCase.executeByClient(fkClient);
                if (jobs.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                return ResponseEntity.status(200).body(JobMapper.toDTOList(jobs));
        }

        @Operation(summary = "Update a job", description = "This method is responsible for updating a job.")
        @PatchMapping("/{id}")
        public ResponseEntity<JobResponseDTO> updateJob(
                        @PathVariable UUID id,
                        @RequestBody @Valid JobRequestDTO jobDto) {
            Client client = getClientUseCase.execute(jobDto.getFkClient());
                Job job = JobMapper.toDomain(jobDto, id, client);
                updateJobUseCase.execute(job);
                return ResponseEntity.ok(JobMapper.toDTO(job));
        }

        @Operation(summary = "Delete a job", description = "This method is responsible for deleting a job.")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteJob(
                        @PathVariable UUID id) {
                deleteJobUseCase.execute(id);
                return ResponseEntity.ok().build();
        }
}
