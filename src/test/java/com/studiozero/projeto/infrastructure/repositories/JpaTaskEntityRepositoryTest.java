package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.infrastructure.entities.TaskEntity;
import com.studiozero.projeto.infrastructure.mappers.TaskEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.TaskRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaTaskEntityRepositoryTest {

    private JpaTaskRepository jpaTaskRepository;
    private TaskRepositoryImpl taskRepository;

    @BeforeEach
    void setUp() {
        jpaTaskRepository = mock(JpaTaskRepository.class);
        taskRepository = new TaskRepositoryImpl(jpaTaskRepository);
    }

    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();
        TaskEntity entity = mock(TaskEntity.class);
        Task task = mock(Task.class);

        when(jpaTaskRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<TaskEntityMapper> mocked = mockStatic(TaskEntityMapper.class)) {
            mocked.when(() -> TaskEntityMapper.toDomain(entity)).thenReturn(task);

            Task result = taskRepository.findById(id);
            assertEquals(task, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(jpaTaskRepository.findById(id)).thenReturn(Optional.empty());

        Task result = taskRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testFindAll() {
        TaskEntity entity = mock(TaskEntity.class);
        Task task = mock(Task.class);

        when(jpaTaskRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<TaskEntityMapper> mocked = mockStatic(TaskEntityMapper.class)) {
            mocked.when(() -> TaskEntityMapper.toDomain(entity)).thenReturn(task);

            List<Task> result = taskRepository.findAll();
            assertEquals(1, result.size());
            assertEquals(task, result.get(0));
        }
    }

    @Test
    void testSave() {
        Task task = mock(Task.class);
        TaskEntity entity = mock(TaskEntity.class);

        try (MockedStatic<TaskEntityMapper> mocked = mockStatic(TaskEntityMapper.class)) {
            mocked.when(() -> TaskEntityMapper.toEntity(task)).thenReturn(entity);

            taskRepository.save(task);
            verify(jpaTaskRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        taskRepository.deleteById(id);
        verify(jpaTaskRepository).deleteById(id);
    }

    @Test
    void testFindByTodayDate() {
        LocalDate today = LocalDate.now();
        TaskEntity entity = mock(TaskEntity.class);
        Task task = mock(Task.class);

        when(jpaTaskRepository.findAllByLimitDate(today)).thenReturn(List.of(entity));
        try (MockedStatic<TaskEntityMapper> mocked = mockStatic(TaskEntityMapper.class)) {
            mocked.when(() -> TaskEntityMapper.toDomainList(List.of(entity))).thenReturn(List.of(task));

            List<Task> result = taskRepository.findByTodayDate(today);
            assertEquals(1, result.size());
            assertEquals(task, result.get(0));
        }
    }
}