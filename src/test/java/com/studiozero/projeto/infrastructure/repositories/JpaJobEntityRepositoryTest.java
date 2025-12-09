package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.mappers.JobEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.JobRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaJobEntityRepositoryTest {

    private JpaJobRepository jpaJobRepository;
    private JobRepositoryImpl jobRepository;

    @BeforeEach
    void setUp() {
        jpaJobRepository = mock(JpaJobRepository.class);
        jobRepository = new JobRepositoryImpl(jpaJobRepository);
    }

    @Test
    void testFindAllByClientId() {
        UUID clientId = UUID.randomUUID();
        JobEntity entity1 = mock(JobEntity.class);
        JobEntity entity2 = mock(JobEntity.class);
        Job job1 = mock(Job.class);
        Job job2 = mock(Job.class);
        Client client1 = mock(Client.class);
        Client client2 = mock(Client.class);

        when(jpaJobRepository.findAll()).thenReturn(List.of(entity1, entity2));
        try (MockedStatic<JobEntityMapper> mocked = mockStatic(JobEntityMapper.class)) {
            mocked.when(() -> JobEntityMapper.toDomain(entity1)).thenReturn(job1);
            mocked.when(() -> JobEntityMapper.toDomain(entity2)).thenReturn(job2);
            when(job1.getClient()).thenReturn(client1);
            when(job2.getClient()).thenReturn(client2);
            when(client1.getId()).thenReturn(clientId);
            when(client2.getId()).thenReturn(UUID.randomUUID());

            List<Job> result = jobRepository.findAllByClientId(clientId);
            assertEquals(1, result.size());
            assertEquals(job1, result.get(0));
        }
    }

    @Test
    void testSumTotalValueByCategoryAndStatus() {
        JobCategory category = JobCategory.MUSIC_REHEARSAL;
        Status status = Status.CLOSED;
        Double expected = 123.45;

        when(jpaJobRepository.sumTotalValueByCategoryAndStatus(category, status)).thenReturn(expected);

        Double result = jobRepository.sumTotalValueByCategoryAndStatus(category, status);
        assertEquals(expected, result);
    }

    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();
        JobEntity entity = mock(JobEntity.class);
        Job job = mock(Job.class);

        when(jpaJobRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<JobEntityMapper> mocked = mockStatic(JobEntityMapper.class)) {
            mocked.when(() -> JobEntityMapper.toDomain(entity)).thenReturn(job);

            Job result = jobRepository.findById(id);
            assertEquals(job, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(jpaJobRepository.findById(id)).thenReturn(Optional.empty());

        Job result = jobRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        Job job = mock(Job.class);
        JobEntity entity = mock(JobEntity.class);

        try (MockedStatic<JobEntityMapper> mocked = mockStatic(JobEntityMapper.class)) {
            mocked.when(() -> JobEntityMapper.toEntity(job)).thenReturn(entity);

            jobRepository.save(job);
            verify(jpaJobRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        jobRepository.deleteById(id);
        verify(jpaJobRepository).deleteById(id);
    }
}