package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.application.dtos.request.TaskUpdateRequestDTO;
import com.studiozero.projeto.application.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.web.mappers.TaskMapper;
import com.studiozero.projeto.application.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Endpoints for Task Management")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Operation(
            summary = "Create a task",
            description = "This method is responsible for create a task."
    )
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody @Valid TaskRequestDTO taskDto
    ) {
        Task savedTask = taskService.createTask(taskDto);
        TaskResponseDTO savedDto = TaskMapper.toDTO(savedTask);
        return ResponseEntity.status(201).body(savedDto);
    }

    @Operation(
            summary = "List all task",
            description = "This method is responsible for list all task."
    )
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks() {
        List<Task> tasks = taskService.listTasks();
        List<TaskResponseDTO> taskDtos = TaskMapper.toListDtos(tasks);
        return ResponseEntity.ok(taskDtos); // Sempre retorna 200, mesmo com lista vazia
    }

    @Operation(
            summary = "Search a tasks",
            description = "This method is responsible for search a tasks."
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(
            @PathVariable @Valid UUID id
    ) {
        Task task = taskService.findTaskById(id);
        TaskResponseDTO taskDto = TaskMapper.toDTO(task);

        return ResponseEntity.ok(taskDto);
    }

    @Operation(
            summary = "Update a task",
            description = "This method is responsible for update a task."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid TaskUpdateRequestDTO taskDto
    ) {
        Task task = taskMapper.toEntity(taskDto, id);
        Task updatedTask = taskService.updateTask(task);

        return ResponseEntity.ok(TaskMapper.toDTO(updatedTask));
    }

    @Operation(
            summary = "Delete a task",
            description = "This method is responsible for delete a task."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable @Valid UUID id
    ) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
