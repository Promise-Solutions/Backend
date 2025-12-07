package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.task.*;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.TaskRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskUseCasesTest {

    private final TaskRepository taskRepository = mock(TaskRepository.class);

    private final CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(taskRepository);
    private final DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskRepository);
    private final GetTaskUseCase getTaskUseCase = new GetTaskUseCase(taskRepository);
    private final ListTasksUseCase listTasksUseCase = new ListTasksUseCase(taskRepository);
    private final ListTodayTasksUseCase listTodayTasksUseCase = new ListTodayTasksUseCase(taskRepository);
    private final UpdateTaskUseCase updateTaskUseCase = new UpdateTaskUseCase(taskRepository);

    private Task validTask() {
        return new Task(
                UUID.randomUUID(),
                "Example Task",
                "desc",
                LocalDate.now(),
                true
        );
    }

    @Test
    @DisplayName("CreateTask - Should create task when valid")
    void createTaskSuccessfully() {
        Task task = validTask();
        task.setId(null);
        doNothing().when(taskRepository).save(task);

        Task result = createTaskUseCase.execute(task);

        assertNotNull(result.getId());
        verify(taskRepository).save(task);
    }

    @Test
    @DisplayName("CreateTask - Should throw exception when task is null")
    void createTaskNullError() {
        assertThrows(IllegalArgumentException.class, () -> createTaskUseCase.execute(null));
    }

    @Test
    @DisplayName("DeleteTask - Should delete task by ID")
    void deleteTaskSuccessfully() {
        UUID id = UUID.randomUUID();
        doNothing().when(taskRepository).deleteById(id);

        deleteTaskUseCase.execute(id);

        verify(taskRepository).deleteById(id);
    }

    @Test
    @DisplayName("GetTask - Should return task when found")
    void getTaskSuccessfully() {
        UUID id = UUID.randomUUID();
        Task task = validTask();
        when(taskRepository.findById(id)).thenReturn(task);

        Task result = getTaskUseCase.execute(id);

        assertEquals(task, result);
    }

    @Test
    @DisplayName("GetTask - Should return null when task not found")
    void getTaskNotFound() {
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(null);

        Task result = getTaskUseCase.execute(id);

        assertNull(result);
    }

    @Test
    @DisplayName("ListTasks - Should return list of tasks")
    void listTasksSuccessfully() {
        List<Task> tasks = List.of(validTask(), validTask());
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = listTasksUseCase.execute();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("ListTodayTasks - Should return tasks for today")
    void listTodayTasksSuccessfully() {
        LocalDate today = LocalDate.now();
        List<Task> tasks = List.of(validTask());
        when(taskRepository.findByTodayDate(today)).thenReturn(tasks);

        List<Task> result = listTodayTasksUseCase.execute();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("UpdateTask - Should throw exception when task is null")
    void updateTaskNullError() {
        assertThrows(IllegalArgumentException.class, () -> updateTaskUseCase.execute(null));
    }

    @Test
    @DisplayName("UpdateTask - Should throw exception when ID is null")
    void updateTaskInvalidIdError() {
        Task task = validTask();
        task.setId(null);

        assertThrows(IllegalArgumentException.class, () -> updateTaskUseCase.execute(task));
    }
}
