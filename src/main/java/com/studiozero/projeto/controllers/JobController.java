package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.dtos.response.JobResponseDTO;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.mappers.JobMapper;
import com.studiozero.projeto.services.JobService;
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
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Tag(name = "Jobs", description = "Endpoints for Job Management")
public class JobController {

    private final JobService jobService;
    private final JobMapper jobMapper;

    @Operation(
            summary = "Create a new job",
            description = "This method is responsible for create a new job."
    )
    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(
            @RequestBody @Valid JobRequestDTO jobDto
    ) {
        Job savedJob = jobService.createJob(jobDto);
        JobResponseDTO savedDto = JobMapper.toDTO(savedJob);
        return ResponseEntity.status(201).body(savedDto);
    }

    @Operation(
            summary = "Search a job",
            description = "This method is responsible for search a job."
    )
    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> findJobById(
            @PathVariable @Valid UUID id
    ) {
        Job job = jobService.findjobById(id);
        JobResponseDTO jobDto = JobMapper.toDTO(job);

        return ResponseEntity.ok(jobDto);
    }

    @Operation(
            summary = "List all jobs",
            description = "This method is responsible for list all jobs."
    )
    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> listAllJobs() {
        List<Job> jobs = jobService.listJobs();

        if (jobs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<JobResponseDTO> jobDtos = JobMapper.toListDtos(jobs);

        return ResponseEntity.status(302).body(jobDtos);
    }

    @Operation(
            summary = "Update a job",
            description = "This method is responsible for update a job."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(
            @PathVariable UUID id,
            @RequestBody @Valid JobRequestDTO jobDto
    ) {
        Job job = jobMapper.toEntity(jobDto, id);
        Job updatedJob = jobService.updateJob(job);
        return ResponseEntity.ok(JobMapper.toDTO(updatedJob));
    }

    @Operation(
            summary = "Delete a job",
            description = "This method is responsible for delete a job."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(
            @PathVariable UUID id
    ) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
}
