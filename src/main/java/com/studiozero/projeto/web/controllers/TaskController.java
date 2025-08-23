package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.employee.GetEmployeeUseCase;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.web.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.web.dtos.request.TaskUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.web.mappers.TaskMapper;
import com.studiozero.projeto.application.usecases.task.CreateTaskUseCase;
import com.studiozero.projeto.application.usecases.task.GetTaskUseCase;
import com.studiozero.projeto.application.usecases.task.UpdateTaskUseCase;
import com.studiozero.projeto.application.usecases.task.DeleteTaskUseCase;
import com.studiozero.projeto.application.usecases.task.ListTasksUseCase;
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

        private final CreateTaskUseCase createTaskUseCase;
        private final GetTaskUseCase getTaskUseCase;
        private final UpdateTaskUseCase updateTaskUseCase;
        private final DeleteTaskUseCase deleteTaskUseCase;
        private final ListTasksUseCase listTasksUseCase;
        private final GetEmployeeUseCase getEmployeeUseCase;

        @Operation(summary = "Create a task", description = "This method is responsible for create a task.")
        @PostMapping
        public ResponseEntity<TaskResponseDTO> createTask(
                        @RequestBody @Valid TaskRequestDTO taskDto) {

            Employee employee = getEmployeeUseCase.execute(taskDto.getFkEmployee());
            Employee assign = getEmployeeUseCase.execute(taskDto.getFkAssigned());

             Task task = TaskMapper.toDomain(taskDto, employee, assign);
                Task savedTask = createTaskUseCase.execute(task);
                TaskResponseDTO savedDto = TaskMapper.toDTO(savedTask);
                return ResponseEntity.status(201).body(savedDto);
        }

        @Operation(summary = "List all task", description = "This method is responsible for list all task.")
        @GetMapping
        public ResponseEntity<List<TaskResponseDTO>> findAllTasks() {
                List<Task> tasks = listTasksUseCase.execute();
                List<TaskResponseDTO> taskDtos = TaskMapper.toDTOList(tasks);
                return ResponseEntity.ok(taskDtos); // Sempre retorna 200, mesmo com lista vazia
        }

        @Operation(summary = "Search a tasks", description = "This method is responsible for search a tasks.")
        @GetMapping("/{id}")
        public ResponseEntity<TaskResponseDTO> findTaskById(
                        @PathVariable @Valid UUID id) {
                Task task = getTaskUseCase.execute(id);
                TaskResponseDTO taskDto = TaskMapper.toDTO(task);
                return ResponseEntity.ok(taskDto);
        }

        @Operation(summary = "Update a task", description = "This method is responsible for update a task.")
        @PatchMapping("/{id}")
        public ResponseEntity<TaskResponseDTO> updateTask(
                        @PathVariable @Valid UUID id,
                        @RequestBody @Valid TaskUpdateRequestDTO taskDto) {

            Employee employee = getEmployeeUseCase.execute(taskDto.getFkEmployee());
            Employee assign = getEmployeeUseCase.execute(taskDto.getFkAssigned());

                Task task = TaskMapper.toDomain(taskDto, id, employee, assign);
                Task updatedTask = updateTaskUseCase.execute(task);
                return ResponseEntity.ok(TaskMapper.toDTO(updatedTask));
        }

        @Operation(summary = "Delete a task", description = "This method is responsible for delete a task.")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTask(
                        @PathVariable @Valid UUID id) {
                deleteTaskUseCase.execute(id);
                return ResponseEntity.ok().build();
        }
}
