package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.job.*;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobUseCasesTest {

    @Mock
    private JobRepository jobRepository;

    @Nested
    class CreateJobUseCaseTests {
        private CreateJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateJobUseCase(jobRepository);
        }

        @Test
        void shouldCreateJobSuccessfully() {
            Job job = new Job();
            job.setTitle("Software Development");

            useCase.execute(job);

            assertNotNull(job.getId());
            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldCreateJobWithExistingId() {
            UUID existingId = UUID.randomUUID();
            Job job = new Job();
            job.setId(existingId);
            job.setTitle("Design Project");

            useCase.execute(job);

            assertEquals(existingId, job.getId());
            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldThrowExceptionWhenJobIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Job inválido", exception.getMessage());
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldGenerateIdWhenJobHasNoId() {
            Job job = new Job();
            job.setTitle("Consulting");
            assertNull(job.getId());

            useCase.execute(job);

            assertNotNull(job.getId());
            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldNotGenerateNewIdWhenJobAlreadyHasOne() {
            UUID originalId = UUID.randomUUID();
            Job job = new Job();
            job.setId(originalId);
            job.setTitle("Marketing Campaign");

            useCase.execute(job);

            assertEquals(originalId, job.getId());
            verify(jobRepository, times(1)).save(job);
        }
    }

    @Nested
    class DeleteJobUseCaseTests {
        private DeleteJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteJobUseCase(jobRepository);
        }

        @Test
        void shouldDeleteJobSuccessfully() {
            UUID jobId = UUID.randomUUID();

            useCase.execute(jobId);

            verify(jobRepository, times(1)).deleteById(jobId);
        }

        @Test
        void shouldDeleteJobWithoutCheckingExistence() {
            UUID jobId = UUID.randomUUID();

            useCase.execute(jobId);

            verify(jobRepository, times(1)).deleteById(jobId);
            verify(jobRepository, never()).findById(any());
        }

        @Test
        void shouldCallRepositoryDeleteExactlyOnce() {
            UUID jobId = UUID.randomUUID();

            useCase.execute(jobId);

            verify(jobRepository, times(1)).deleteById(jobId);
            verifyNoMoreInteractions(jobRepository);
        }
    }

    @Nested
    class GetJobUseCaseTests {
        private GetJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetJobUseCase(jobRepository);
        }

        @Test
        void shouldReturnJobWhenFound() {
            UUID jobId = UUID.randomUUID();
            Job expectedJob = new Job();
            expectedJob.setId(jobId);
            expectedJob.setTitle("Web Development");;

            when(jobRepository.findById(jobId)).thenReturn(expectedJob);

            Job result = useCase.execute(jobId);

            assertNotNull(result);
            assertEquals(jobId, result.getId());
            assertEquals("Web Development", result.getTitle());
            verify(jobRepository, times(1)).findById(jobId);
        }

        @Test
        void shouldReturnNullWhenJobNotFound() {
            UUID jobId = UUID.randomUUID();

            when(jobRepository.findById(jobId)).thenReturn(null);

            Job result = useCase.execute(jobId);

            assertNull(result);
            verify(jobRepository, times(1)).findById(jobId);
        }

        @Test
        void shouldReturnSameJobInstanceFromRepository() {
            UUID jobId = UUID.randomUUID();
            Job expectedJob = new Job();
            expectedJob.setId(jobId);

            when(jobRepository.findById(jobId)).thenReturn(expectedJob);

            Job result = useCase.execute(jobId);

            assertSame(expectedJob, result);
        }

        @Test
        void shouldCallRepositoryFindByIdExactlyOnce() {
            UUID jobId = UUID.randomUUID();

            when(jobRepository.findById(jobId)).thenReturn(null);

            useCase.execute(jobId);

            verify(jobRepository, times(1)).findById(jobId);
            verifyNoMoreInteractions(jobRepository);
        }
    }

    @Nested
    class ListJobsUseCaseTests {
        private ListJobsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListJobsUseCase(jobRepository);
        }

        @Test
        void shouldReturnPageOfJobs() {
            Job job1 = new Job();
            job1.setId(UUID.randomUUID());
            job1.setTitle("Job 1");

            Job job2 = new Job();
            job2.setId(UUID.randomUUID());
            job2.setTitle("Job 2");

            Pageable pageable = PageRequest.of(0, 10);
            Page<Job> expectedPage = new PageImpl<>(Arrays.asList(job1, job2), pageable, 2);

            when(jobRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Job> result = useCase.execute(pageable);

            assertNotNull(result);
            assertEquals(2, result.getContent().size());
            assertEquals("Job 1", result.getContent().get(0).getTitle());
            assertEquals("Job 2", result.getContent().get(1).getTitle());
            verify(jobRepository, times(1)).findAll(pageable);
        }

        @Test
        void shouldReturnEmptyPageWhenNoJobsExist() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Job> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

            when(jobRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Job> result = useCase.execute(pageable);

            assertNotNull(result);
            assertTrue(result.getContent().isEmpty());
            assertEquals(0, result.getTotalElements());
            verify(jobRepository, times(1)).findAll(pageable);
        }

        @Test
        void shouldReturnJobsByClient() {
            UUID clientId = UUID.randomUUID();

            Job job1 = new Job();
            job1.setId(UUID.randomUUID());
            job1.setTitle("Client Job 1");

            Job job2 = new Job();
            job2.setId(UUID.randomUUID());
            job2.setTitle("Client Job 2");

            List<Job> expectedJobs = Arrays.asList(job1, job2);

            when(jobRepository.findAllByClientId(clientId)).thenReturn(expectedJobs);

            List<Job> result = useCase.executeByClient(clientId);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Client Job 1", result.get(0).getTitle());
            assertEquals("Client Job 2", result.get(1).getTitle());
            verify(jobRepository, times(1)).findAllByClientId(clientId);
        }

        @Test
        void shouldReturnEmptyListWhenClientHasNoJobs() {
            UUID clientId = UUID.randomUUID();

            when(jobRepository.findAllByClientId(clientId)).thenReturn(Collections.emptyList());

            List<Job> result = useCase.executeByClient(clientId);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(jobRepository, times(1)).findAllByClientId(clientId);
        }

        @Test
        void shouldReturnSamePageInstanceFromRepository() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Job> expectedPage = new PageImpl<>(Arrays.asList(new Job(), new Job()));

            when(jobRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Job> result = useCase.execute(pageable);

            assertSame(expectedPage, result);
        }

        @Test
        void shouldReturnSameListInstanceFromRepositoryForClient() {
            UUID clientId = UUID.randomUUID();
            List<Job> expectedList = Arrays.asList(new Job(), new Job());

            when(jobRepository.findAllByClientId(clientId)).thenReturn(expectedList);

            List<Job> result = useCase.executeByClient(clientId);

            assertSame(expectedList, result);
        }

        @Test
        void shouldHandleDifferentPageSizes() {
            Pageable pageable = PageRequest.of(0, 5);
            Page<Job> expectedPage = new PageImpl<>(Arrays.asList(new Job(), new Job()), pageable, 2);

            when(jobRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Job> result = useCase.execute(pageable);

            assertNotNull(result);
            assertEquals(5, result.getSize());
            verify(jobRepository, times(1)).findAll(pageable);
        }
    }

    @Nested
    class UpdateJobUseCaseTests {
        private UpdateJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateJobUseCase(jobRepository);
        }

        @Test
        void shouldUpdateJobSuccessfully() {
            UUID jobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(jobId);
            job.setTitle("Updated Job");

            useCase.execute(job);

            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldThrowExceptionWhenJobIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Job inválido", exception.getMessage());
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            Job job = new Job();
            job.setTitle("Test Job");

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(job));

            assertEquals("Job inválido", exception.getMessage());
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldUpdateJobWithAllFields() {
            UUID jobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(jobId);
            job.setTitle("Complete Job");

            useCase.execute(job);

            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldNotCheckExistenceBeforeUpdating() {
            UUID jobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(jobId);
            job.setTitle("Job without check");

            useCase.execute(job);

            verify(jobRepository, times(1)).save(job);
            verify(jobRepository, never()).findById(any());
        }

        @Test
        void shouldValidateJobBeforeSaving() {
            assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));

            verify(jobRepository, never()).save(any());
        }
    }
}
