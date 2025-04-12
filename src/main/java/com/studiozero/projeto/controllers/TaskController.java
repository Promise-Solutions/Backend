package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Endpoints for Task Management")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(
            summary = "Create a task",
            description = "This method is responsible for create a task."
    )
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO taskDto) {
        return ResponseEntity.status(201).body(taskService.save(taskDto));
    }

    @Operation(
            summary = "List all task",
            description = "This method is responsible for list all task."
    )
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @Operation(
            summary = "Search a tasks",
            description = "This method is responsible for search a tasks."
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable @Valid UUID id){
        return ResponseEntity.ok(taskService.findById(id));
    }

    @Operation(
            summary = "Update a task",
            description = "This method is responsible for update a task."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid TaskRequestDTO taskDto) {
        return ResponseEntity.ok(taskService.update(id, taskDto));
    }

    @Operation(
            summary = "Delete a task",
            description = "This method is responsible for delete a task."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable @Valid UUID id){
        return ResponseEntity.ok(taskService.delete(id));
    }
}
