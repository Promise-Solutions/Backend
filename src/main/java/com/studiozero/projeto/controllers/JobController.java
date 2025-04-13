package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.dtos.response.JobResponseDTO;
import com.studiozero.projeto.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
@Tag(name = "Jobs", description = "Endpoints for Job Management")
public class JobController {

        @Autowired
        private JobService jobService;

        @Operation(summary = "Create a new job", description = "This method is responsible for create a new job.")
        @PostMapping
        public ResponseEntity<JobResponseDTO> createJob(
                        @RequestBody @Valid JobRequestDTO jobDto) {
                return ResponseEntity.ok(jobService.save(jobDto));
        }

        @Operation(summary = "Search a job", description = "This method is responsible for search a job.")
        @GetMapping("/{id}")
        public ResponseEntity<JobResponseDTO> findJobById(
                        @PathVariable @Valid UUID id) {
                return ResponseEntity.ok(jobService.findById(id));
        }

        @Operation(summary = "List all jobs", description = "This method is responsible for list all jobs.")
        @GetMapping
        public ResponseEntity<List<JobResponseDTO>> listAllJobs() {
                return ResponseEntity.ok(jobService.findAll());
        }

        @Operation(summary = "Update a job", description = "This method is responsible for update a job.")
        @PatchMapping("/{id}")
        public ResponseEntity<JobResponseDTO> updateJob(
                        @PathVariable UUID id,
                        @RequestBody @Valid JobRequestDTO jobDto) {
                return ResponseEntity.ok(jobService.update(id, jobDto));
        }

        @Operation(summary = "Delete a job", description = "This method is responsible for delete a job.")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteJob(
                        @PathVariable UUID id) {
                jobService.delete(id);
                return ResponseEntity.ok().build();
        }
}
