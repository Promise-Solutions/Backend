//package com.studiozero.projeto.applications.web.controllers;
//
//import com.studiozero.projeto.applications.web.dtos.request.*;
//import com.studiozero.projeto.domain.services.JobService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/jobs")
//@Tag(name = "Jobs", description = "Endpoints for Job Management")
//public class JobController {
//
//    @Autowired
//    private JobService jobService;
//
//    @PostMapping("/create")
//    @Operation(
//            summary = "Create job",
//            description = "This method is responsible for create the job to the repository"
//    )
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Job> create(
//            @Valid
//            @RequestBody
//            JobCreateRequestDTO job
//    ) {
//        return ResponseEntity.ok(jobService.save(job));
//    }
//
//    @GetMapping()
//    @Operation(
//            summary = "Search all jobs",
//            description = "This method is responsible for searching the all jobs in the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<List<Job>> searchAll() {
//        return ResponseEntity.ok(jobService.searchAll());
//    }
//
//    @PostMapping("/search")
//    @Operation(
//            summary = "Search job",
//            description = "This method is responsible for searching the job in the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Optional<Job>> search(
//            @Valid
//            @RequestBody
//            JobReadRequestDTO job
//    ) {
//        return ResponseEntity.ok(jobService.read(job));
//    }
//
//    @PutMapping("/update")
//    @Operation(
//            summary = "Update job",
//            description = "This method is responsible for updating the job in the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Job> update(
//            @Valid
//            @RequestBody
//            JobUpdateRequestDTO job
//    ) {
//        return ResponseEntity.ok(jobService.update(job));
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(
//            summary = "Delete job",
//            description = "This method is responsible for deleting the job from the repository"
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Optional<Job>> delete(
//            @Valid
//            @RequestBody
//            JobDeleteRequestDTO job
//    ) {
//        return ResponseEntity.ok(jobService.delete(job));
//    }
//}
