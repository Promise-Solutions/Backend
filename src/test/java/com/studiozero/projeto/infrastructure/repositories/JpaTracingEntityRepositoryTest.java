package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.infrastructure.entities.TracingEntity;
import com.studiozero.projeto.infrastructure.mappers.TracingEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.TracingRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaTracingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaTracingEntityRepositoryTest {

    private JpaTracingRepository jpaTracingRepository;
    private TracingRepositoryImpl tracingRepository;

    @BeforeEach
    void setUp() {
        jpaTracingRepository = mock(JpaTracingRepository.class);
        tracingRepository = new TracingRepositoryImpl(jpaTracingRepository);
    }

    @Test
    void testFindTopByOrderByIdDescSuccess() {
        TracingEntity entity = mock(TracingEntity.class);
        Tracing tracing = mock(Tracing.class);

        when(jpaTracingRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(entity));
        try (MockedStatic<TracingEntityMapper> mocked = mockStatic(TracingEntityMapper.class)) {
            mocked.when(() -> TracingEntityMapper.toDomain(entity)).thenReturn(tracing);

            Tracing result = tracingRepository.findTopByOrderByIdDesc();
            assertEquals(tracing, result);
        }
    }

    @Test
    void testFindTopByOrderByIdDescNotFound() {
        when(jpaTracingRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

        Tracing result = tracingRepository.findTopByOrderByIdDesc();
        assertNull(result);
    }

    @Test
    void testDeleteAllByIdNot() {
        Integer id = 1;
        tracingRepository.deleteAllByIdNot(id);
        verify(jpaTracingRepository).deleteAllByIdNot(id);
    }

    @Test
    void testFindTopByOrderByDateTimeDesc() {
        TracingEntity entity = mock(TracingEntity.class);
        Tracing tracing = mock(Tracing.class);

        when(jpaTracingRepository.findTopByOrderByDateTimeDesc()).thenReturn(entity);
        try (MockedStatic<TracingEntityMapper> mocked = mockStatic(TracingEntityMapper.class)) {
            mocked.when(() -> TracingEntityMapper.toDomain(entity)).thenReturn(tracing);

            Tracing result = tracingRepository.findTopByOrderByDateTimeDesc();
            assertEquals(tracing, result);
        }
    }

    @Test
    void testFindByIdSuccess() {
        Integer id = 1;
        TracingEntity entity = mock(TracingEntity.class);
        Tracing tracing = mock(Tracing.class);

        when(jpaTracingRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<TracingEntityMapper> mocked = mockStatic(TracingEntityMapper.class)) {
            mocked.when(() -> TracingEntityMapper.toDomain(entity)).thenReturn(tracing);

            Tracing result = tracingRepository.findById(id);
            assertEquals(tracing, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 1;
        when(jpaTracingRepository.findById(id)).thenReturn(Optional.empty());

        Tracing result = tracingRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        Tracing tracing = mock(Tracing.class);
        TracingEntity entity = mock(TracingEntity.class);

        try (MockedStatic<TracingEntityMapper> mocked = mockStatic(TracingEntityMapper.class)) {
            mocked.when(() -> TracingEntityMapper.toEntity(tracing)).thenReturn(entity);

            tracingRepository.save(tracing);
            verify(jpaTracingRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        Integer id = 1;
        tracingRepository.deleteById(id);
        verify(jpaTracingRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        TracingEntity entity = mock(TracingEntity.class);
        Tracing tracing = mock(Tracing.class);

        when(jpaTracingRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<TracingEntityMapper> mocked = mockStatic(TracingEntityMapper.class)) {
            mocked.when(() -> TracingEntityMapper.toDomain(entity)).thenReturn(tracing);

            List<Tracing> result = tracingRepository.findAll();
            assertEquals(1, result.size());
            assertEquals(tracing, result.get(0));
        }
    }
}