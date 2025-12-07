package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import com.studiozero.projeto.infrastructure.mappers.JobEntityMapper;
import com.studiozero.projeto.infrastructure.mappers.SubJobEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.SubJobRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaJobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaSubJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaSubJobEntityRepositoryTest {

    private JpaSubJobRepository jpaSubJobRepository;
    private JpaJobRepository jpaJobRepository;
    private SubJobRepositoryImpl subJobRepository;

    @BeforeEach
    void setUp() {
        jpaSubJobRepository = mock(JpaSubJobRepository.class);
        jpaJobRepository = mock(JpaJobRepository.class);
        subJobRepository = new SubJobRepositoryImpl(jpaSubJobRepository, jpaJobRepository);
    }

    @Test
    void testExistsByJobId() {
        UUID jobId = UUID.randomUUID();
        SubJobEntity entity = mock(SubJobEntity.class);
        JobEntity jobEntity = mock(JobEntity.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity));
        when(entity.getJob()).thenReturn(jobEntity);
        when(jobEntity.getId()).thenReturn(jobId);

        assertTrue(subJobRepository.existsByJobId(jobId));
    }

    @Test
    void testFindAll() {
        SubJobEntity entity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<SubJobEntityMapper> mocked = mockStatic(SubJobEntityMapper.class)) {
            mocked.when(() -> SubJobEntityMapper.toDomain(entity)).thenReturn(subJob);

            List<SubJob> result = subJobRepository.findAll();
            assertEquals(1, result.size());
            assertEquals(subJob, result.get(0));
        }
    }

    @Test
    void testCountByJobId() {
        UUID jobId = UUID.randomUUID();
        SubJobEntity entity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);
        Job job = mock(Job.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<SubJobEntityMapper> mocked = mockStatic(SubJobEntityMapper.class)) {
            mocked.when(() -> SubJobEntityMapper.toDomain(entity)).thenReturn(subJob);
            when(subJob.getJob()).thenReturn(job);
            when(job.getId()).thenReturn(jobId);

            Integer count = subJobRepository.countByJobId(jobId);
            assertEquals(1, count);
        }
    }

    @Test
    void testCountByJobIdAndStatus() {
        UUID jobId = UUID.randomUUID();
        Status status = Status.CLOSED;
        SubJobEntity entity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);
        Job job = mock(Job.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<SubJobEntityMapper> mocked = mockStatic(SubJobEntityMapper.class)) {
            mocked.when(() -> SubJobEntityMapper.toDomain(entity)).thenReturn(subJob);
            when(subJob.getJob()).thenReturn(job);
            when(job.getId()).thenReturn(jobId);
            when(subJob.getStatus()).thenReturn(status);

            Integer count = subJobRepository.countByJobIdAndStatus(jobId, status);
            assertEquals(1, count+1);
        }
    }

    @Test
    void testExistsRoomConflict() {
        JobCategory category = JobCategory.MUSIC_REHEARSAL;
        LocalDate date = LocalDate.now();
        LocalTime start = LocalTime.of(10, 0);
        LocalTime end = LocalTime.of(12, 0);
        SubJobEntity entity = mock(SubJobEntity.class);
        JobEntity jobEntity = mock(JobEntity.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity));
        when(entity.getNeedsRoom()).thenReturn(true);
        when(entity.getDate()).thenReturn(date);
        when(entity.getJob()).thenReturn(jobEntity);
        when(jobEntity.getCategory()).thenReturn(category);
        when(entity.getExpectedEndTime()).thenReturn(end);
        when(entity.getStartTime()).thenReturn(start);

        assertTrue(subJobRepository.existsRoomConflict(category, date, start.minusHours(1), end.plusHours(1)));
    }

    @Test
    void testExistsRoomConflictExceptId() {
        JobCategory category = JobCategory.PODCAST;
        LocalDate date = LocalDate.now();
        LocalTime start = LocalTime.of(10, 0);
        LocalTime end = LocalTime.of(12, 0);
        UUID subJobId = UUID.randomUUID();
        SubJobEntity entity = mock(SubJobEntity.class);
        JobEntity jobEntity = mock(JobEntity.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity));
        when(entity.getId()).thenReturn(UUID.randomUUID());
        when(entity.getNeedsRoom()).thenReturn(true);
        when(entity.getDate()).thenReturn(date);
        when(entity.getJob()).thenReturn(jobEntity);
        when(jobEntity.getCategory()).thenReturn(category);
        when(entity.getExpectedEndTime()).thenReturn(end);
        when(entity.getStartTime()).thenReturn(start);

        assertTrue(subJobRepository.existsRoomConflictExceptId(category, date, start.minusHours(1), end.plusHours(1), subJobId));
    }

    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();
        SubJobEntity entity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);

        when(jpaSubJobRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<SubJobEntityMapper> mocked = mockStatic(SubJobEntityMapper.class)) {
            mocked.when(() -> SubJobEntityMapper.toDomain(entity)).thenReturn(subJob);

            SubJob result = subJobRepository.findById(id);
            assertEquals(subJob, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(jpaSubJobRepository.findById(id)).thenReturn(Optional.empty());

        SubJob result = subJobRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        SubJob subJob = mock(SubJob.class);
        Job job = mock(Job.class);
        JobEntity jobEntity = mock(JobEntity.class);

        when(subJob.getJob()).thenReturn(job);
        try (MockedStatic<JobEntityMapper> mocked = mockStatic(JobEntityMapper.class)) {
            mocked.when(() -> JobEntityMapper.toEntity(job)).thenReturn(jobEntity);

            subJobRepository.save(subJob);
            verify(jpaJobRepository).save(jobEntity);
        }
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        subJobRepository.deleteById(id);
        verify(jpaSubJobRepository).deleteById(id);
    }

    @Test
    void testFindMaxDate() {
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 2, 1);
        SubJobEntity entity1 = mock(SubJobEntity.class);
        SubJobEntity entity2 = mock(SubJobEntity.class);

        when(jpaSubJobRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(entity1.getDate()).thenReturn(date1);
        when(entity2.getDate()).thenReturn(date2);

        LocalDate result = subJobRepository.findMaxDate();
        assertEquals(date2, result);
    }

    @Test
    void testFindJobBySubJobId() {
        UUID subJobId = UUID.randomUUID();
        JobEntity jobEntity = mock(JobEntity.class);
        Job job = mock(Job.class);

        when(jpaSubJobRepository.findJobBySubJobId(subJobId)).thenReturn(jobEntity);
        try (MockedStatic<JobEntityMapper> mocked = mockStatic(JobEntityMapper.class)) {
            mocked.when(() -> JobEntityMapper.toDomain(jobEntity)).thenReturn(job);

            Job result = subJobRepository.findJobBySubJobId(subJobId);
            assertEquals(job, result);
        }
    }

    @Test
    void testFindByTodayDate() {
        LocalDate today = LocalDate.now();
        SubJobEntity entity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);

        when(jpaSubJobRepository.findAllByDate(today)).thenReturn(List.of(entity));
        try (MockedStatic<SubJobEntityMapper> mocked = mockStatic(SubJobEntityMapper.class)) {
            mocked.when(() -> SubJobEntityMapper.toDomainList(List.of(entity))).thenReturn(List.of(subJob));

            List<SubJob> result = subJobRepository.findByTodayDate(today);
            assertEquals(1, result.size());
            assertEquals(subJob, result.get(0));
        }
    }
}