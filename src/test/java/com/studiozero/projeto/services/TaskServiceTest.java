package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.TaskService;
import com.studiozero.projeto.application.services.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.infrastructure.configs.exceptions.NotFoundException;
import com.studiozero.projeto.web.controllers.mappers.TaskMapper;
import com.studiozero.projeto.domain.entities.repositories.EmployeeRepository;
import com.studiozero.projeto.domain.entities.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskMapper taskMapper;

    private Task task;
    private UUID taskId;

    @BeforeEach
    void setUp() {
        taskId = UUID.randomUUID();
        task = new Task();
        task.setId(taskId);
    }

    @Test
    @DisplayName("Should create task successfully with employee")
    void createTask_WithEmployee() {
        UUID employeeId = UUID.randomUUID();
        Employee employee = new Employee();
        employee.setId(employeeId);

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setFkEmployee(employeeId);

        when(taskMapper.toEntity(dto)).thenReturn(task);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(taskRepository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        Task created = taskService.createTask(dto);

        assertNotNull(created);
        assertEquals(employee, created.getEmployee());
        verify(taskRepository).save(task);
    }

    @Test
    @DisplayName("Should create task successfully without employee")
    void createTask_WithoutEmployee() {
        TaskRequestDTO dto = new TaskRequestDTO();

        when(taskMapper.toEntity(dto)).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        Task created = taskService.createTask(dto);

        assertNotNull(created);
        assertNull(created.getEmployee());
        verify(taskRepository).save(task);
    }

    @Test
    @DisplayName("Should find task by ID successfully")
    void findTaskById_Success() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task found = taskService.findTaskById(taskId);

        assertNotNull(found);
        assertEquals(taskId, found.getId());
    }

    @Test
    @DisplayName("Should throw NotFoundException when task not found by ID")
    void findTaskById_NotFound() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> taskService.findTaskById(taskId));
    }

    @Test
    @DisplayName("Should list all tasks")
    void listTasks() {
        List<Task> taskList = List.of(task);
        when(taskRepository.findAll()).thenReturn(taskList);

        List<Task> result = taskService.listTasks();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should update task successfully")
    void updateTask_Success() {
        when(taskRepository.existsById(task.getId())).thenReturn(true);
        when(taskRepository.save(task)).thenReturn(task);

        Task updated = taskService.updateTask(task);

        assertNotNull(updated);
        verify(taskRepository).save(task);
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existent task")
    void updateTask_NotFound() {
        when(taskRepository.existsById(task.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> taskService.updateTask(task));
    }

    @Test
    @DisplayName("Should delete task successfully")
    void deleteTask_Success() {
        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        verify(taskRepository).deleteById(taskId);
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existent task")
    void deleteTask_NotFound() {
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> taskService.deleteTask(taskId));
    }
}
