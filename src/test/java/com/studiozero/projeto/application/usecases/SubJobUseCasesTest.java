package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.application.usecases.subjob.*;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import jakarta.persistence.EntityNotFoundException;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubJobUseCasesTest {

    @Mock
    private SubJobRepository subJobRepository;

    @Mock
    private JobRepository jobRepository;

    @Nested
    class CreateSubJobUseCaseTests {
        private CreateSubJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateSubJobUseCase(subJobRepository, jobRepository);
        }

        @Test
        void shouldThrowExceptionWhenJobNotFound() {
            UUID jobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(jobId);

            SubJob subJob = new SubJob();
            subJob.setJob(job);

            when(jobRepository.findById(jobId)).thenReturn(null);

            EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                    () -> useCase.execute(subJob));

            assertEquals("Serviço não encontrado para criar o subserviço", exception.getMessage());
            verify(jobRepository, times(1)).findById(jobId);
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenRoomConflictExists() {
            UUID jobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(jobId);
            job.setCategory(job.getCategory());

            SubJob subJob = new SubJob();
            subJob.setJob(job);
            subJob.setNeedsRoom(true);
            subJob.setDate(LocalDate.now());
            subJob.setStartTime(LocalTime.of(10, 0));
            subJob.setExpectedEndTime(LocalTime.of(12, 0));

            when(jobRepository.findById(jobId)).thenReturn(job);
            when(subJobRepository.existsRoomConflict(
                    job.getCategory(), subJob.getDate(), subJob.getStartTime(), subJob.getExpectedEndTime()
            )).thenReturn(true);

            IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> useCase.execute(subJob));

            assertEquals("There is a room usage conflict", exception.getMessage());
            verify(jobRepository, never()).save(any());
        }
    }

    @Nested
    class DeleteSubJobUseCaseTests {
        private DeleteSubJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteSubJobUseCase(subJobRepository, jobRepository);
        }

        @Test
        void shouldDeleteSubJobSuccessfully() {
            UUID subJobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(UUID.randomUUID());

            SubJob subJob = new SubJob();
            subJob.setId(subJobId);

            job.setSubJobs(new ArrayList<>(List.of(subJob)));

            when(subJobRepository.findJobBySubJobId(subJobId)).thenReturn(job);

            SubJob result = useCase.execute(subJobId);

            assertNotNull(result);
            verify(subJobRepository, times(1)).findJobBySubJobId(subJobId);
            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldThrowExceptionWhenSubJobNotFound() {
            UUID subJobId = UUID.randomUUID();

            when(subJobRepository.findJobBySubJobId(subJobId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(subJobId));

            assertEquals("SubJob not found", exception.getMessage());
            verify(subJobRepository, times(1)).findJobBySubJobId(subJobId);
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldReturnRemovedSubJob() {
            UUID subJobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(UUID.randomUUID());

            SubJob subJob = new SubJob();
            subJob.setId(subJobId);
            subJob.setDescription("Test SubJob");

            job.setSubJobs(new ArrayList<>(List.of(subJob)));

            when(subJobRepository.findJobBySubJobId(subJobId)).thenReturn(job);

            SubJob result = useCase.execute(subJobId);

            assertNotNull(result);
            assertEquals(subJobId, result.getId());
        }
    }

    @Nested
    class GetSubJobUseCaseTests {
        private GetSubJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetSubJobUseCase(subJobRepository);
        }

        @Test
        void shouldReturnSubJobWhenFound() {
            UUID subJobId = UUID.randomUUID();
            SubJob expectedSubJob = new SubJob();
            expectedSubJob.setId(subJobId);
            expectedSubJob.setDescription("Test SubJob");

            when(subJobRepository.findById(subJobId)).thenReturn(expectedSubJob);

            SubJob result = useCase.execute(subJobId);

            assertNotNull(result);
            assertEquals(subJobId, result.getId());
            assertEquals("Test SubJob", result.getDescription());
            verify(subJobRepository, times(1)).findById(subJobId);
        }

        @Test
        void shouldThrowExceptionWhenSubJobNotFound() {
            UUID subJobId = UUID.randomUUID();

            when(subJobRepository.findById(subJobId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(subJobId));

            assertEquals("Sub job not found", exception.getMessage());
            verify(subJobRepository, times(1)).findById(subJobId);
        }

        @Test
        void shouldReturnSameSubJobInstanceFromRepository() {
            UUID subJobId = UUID.randomUUID();
            SubJob expectedSubJob = new SubJob();
            expectedSubJob.setId(subJobId);

            when(subJobRepository.findById(subJobId)).thenReturn(expectedSubJob);

            SubJob result = useCase.execute(subJobId);

            assertSame(expectedSubJob, result);
        }
    }

    @Nested
    class ListSubJobsByFkServiceUseCaseTests {
        private ListSubJobsByFkServiceUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListSubJobsByFkServiceUseCase(subJobRepository);
        }

        @Test
        void shouldReturnPageOfSubJobsByService() {
            UUID serviceId = UUID.randomUUID();

            SubJob subJob1 = new SubJob();
            subJob1.setId(UUID.randomUUID());
            subJob1.setDescription("SubJob 1");

            SubJob subJob2 = new SubJob();
            subJob2.setId(UUID.randomUUID());
            subJob2.setDescription("SubJob 2");

            Pageable pageable = PageRequest.of(0, 10);
            Page<SubJob> expectedPage = new PageImpl<>(Arrays.asList(subJob1, subJob2), pageable, 2);

            when(subJobRepository.findAllByJob(serviceId, pageable)).thenReturn(expectedPage);

            Page<SubJob> result = useCase.execute(serviceId, pageable);

            assertNotNull(result);
            assertEquals(2, result.getContent().size());
            assertEquals("SubJob 1", result.getContent().get(0).getDescription());
            assertEquals("SubJob 2", result.getContent().get(1).getDescription());
            verify(subJobRepository, times(1)).findAllByJob(serviceId, pageable);
        }

        @Test
        void shouldReturnEmptyPageWhenNoSubJobsExist() {
            UUID serviceId = UUID.randomUUID();
            Pageable pageable = PageRequest.of(0, 10);
            Page<SubJob> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

            when(subJobRepository.findAllByJob(serviceId, pageable)).thenReturn(expectedPage);

            Page<SubJob> result = useCase.execute(serviceId, pageable);

            assertNotNull(result);
            assertTrue(result.getContent().isEmpty());
            verify(subJobRepository, times(1)).findAllByJob(serviceId, pageable);
        }

        @Test
        void shouldReturnSamePageInstanceFromRepository() {
            UUID serviceId = UUID.randomUUID();
            Pageable pageable = PageRequest.of(0, 10);
            Page<SubJob> expectedPage = new PageImpl<>(Arrays.asList(new SubJob(), new SubJob()));

            when(subJobRepository.findAllByJob(serviceId, pageable)).thenReturn(expectedPage);

            Page<SubJob> result = useCase.execute(serviceId, pageable);

            assertSame(expectedPage, result);
        }
    }

    @Nested
    class ListSubJobsUseCaseTests {
        private ListSubJobsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListSubJobsUseCase(subJobRepository);
        }

        @Test
        void shouldReturnListOfSubJobs() {
            SubJob subJob1 = new SubJob();
            subJob1.setId(UUID.randomUUID());
            subJob1.setDescription("SubJob 1");

            SubJob subJob2 = new SubJob();
            subJob2.setId(UUID.randomUUID());
            subJob2.setDescription("SubJob 2");

            List<SubJob> expectedSubJobs = Arrays.asList(subJob1, subJob2);

            when(subJobRepository.findAll()).thenReturn(expectedSubJobs);

            List<SubJob> result = useCase.execute();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("SubJob 1", result.get(0).getDescription());
            assertEquals("SubJob 2", result.get(1).getDescription());
            verify(subJobRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnEmptyListWhenNoSubJobsExist() {
            when(subJobRepository.findAll()).thenReturn(Collections.emptyList());

            List<SubJob> result = useCase.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(subJobRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnSameListInstanceFromRepository() {
            List<SubJob> expectedList = Arrays.asList(new SubJob(), new SubJob());

            when(subJobRepository.findAll()).thenReturn(expectedList);

            List<SubJob> result = useCase.execute();

            assertSame(expectedList, result);
        }
    }

    @Nested
    class ListTodaySubJobsUseCaseTests {
        private ListTodaySubJobsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListTodaySubJobsUseCase(subJobRepository);
        }

        @Test
        void shouldReturnTodaySubJobs() {
            LocalDate today = LocalDate.now();

            SubJob subJob1 = new SubJob();
            subJob1.setId(UUID.randomUUID());
            subJob1.setDate(today);
            subJob1.setDescription("Today SubJob 1");

            SubJob subJob2 = new SubJob();
            subJob2.setId(UUID.randomUUID());
            subJob2.setDate(today);
            subJob2.setDescription("Today SubJob 2");

            List<SubJob> expectedSubJobs = Arrays.asList(subJob1, subJob2);

            when(subJobRepository.findByTodayDate(today)).thenReturn(expectedSubJobs);

            List<SubJob> result = useCase.execute();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Today SubJob 1", result.get(0).getDescription());
            assertEquals("Today SubJob 2", result.get(1).getDescription());
            verify(subJobRepository, times(1)).findByTodayDate(today);
        }

        @Test
        void shouldReturnEmptyListWhenNoSubJobsToday() {
            LocalDate today = LocalDate.now();

            when(subJobRepository.findByTodayDate(today)).thenReturn(Collections.emptyList());

            List<SubJob> result = useCase.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(subJobRepository, times(1)).findByTodayDate(today);
        }

        @Test
        void shouldUseTodayDate() {
            LocalDate today = LocalDate.now();

            when(subJobRepository.findByTodayDate(today)).thenReturn(Collections.emptyList());

            useCase.execute();

            verify(subJobRepository, times(1)).findByTodayDate(today);
        }
    }

    @Nested
    class UpdateSubJobStatusUseCaseTests {
        private UpdateSubJobStatusUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateSubJobStatusUseCase(subJobRepository, jobRepository);
        }

        @Test
        void shouldUpdateSubJobStatusSuccessfully() {
            UUID subJobId = UUID.randomUUID();
            UUID jobId = UUID.randomUUID();
            Status newStatus = Status.CLOSED;

            SubJob subJob = new SubJob();
            subJob.setId(subJobId);
            subJob.setStatus(Status.OPEN);

            Job job = new Job();
            job.setId(jobId);
            job.setSubJobs(new ArrayList<>(List.of(subJob)));

            when(jobRepository.findById(jobId)).thenReturn(job);

            SubJob result = useCase.execute(subJobId, newStatus, jobId);

            assertNotNull(result);
            verify(jobRepository, times(1)).findById(jobId);
            verify(jobRepository, times(1)).save(job);
        }

        @Test
        void shouldThrowExceptionWhenJobNotFound() {
            UUID subJobId = UUID.randomUUID();
            UUID jobId = UUID.randomUUID();
            Status newStatus = Status.CLOSED;

            when(jobRepository.findById(jobId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(subJobId, newStatus, jobId));

            assertEquals("Serviço não encontrado para mudar status", exception.getMessage());
            verify(jobRepository, times(1)).findById(jobId);
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenSubJobNotFoundInJob() {
            UUID subJobId = UUID.randomUUID();
            UUID jobId = UUID.randomUUID();
            Status newStatus = Status.CLOSED;

            Job job = new Job();
            job.setId(jobId);
            job.setSubJobs(new ArrayList<>());

            when(jobRepository.findById(jobId)).thenReturn(job);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(subJobId, newStatus, jobId));

            assertEquals("subserviço não encontrado para mudar status", exception.getMessage());
            verify(jobRepository, never()).save(any());
        }

        @Test
        void shouldReturnUpdatedSubJob() {
            UUID subJobId = UUID.randomUUID();
            UUID jobId = UUID.randomUUID();
            Status newStatus = Status.CLOSED;

            SubJob subJob = new SubJob();
            subJob.setId(subJobId);
            subJob.setDescription("Test SubJob");

            Job job = new Job();
            job.setId(jobId);
            job.setSubJobs(new ArrayList<>(List.of(subJob)));

            when(jobRepository.findById(jobId)).thenReturn(job);

            SubJob result = useCase.execute(subJobId, newStatus, jobId);

            assertNotNull(result);
            assertEquals(subJobId, result.getId());
        }
    }

    @Nested
    class UpdateSubJobUseCaseTests {
        private UpdateSubJobUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateSubJobUseCase(subJobRepository, jobRepository);
        }

        @Test
        void shouldThrowExceptionWhenRoomConflictExists() {
            UUID subJobId = UUID.randomUUID();
            Job job = new Job();
            job.setId(UUID.randomUUID());
            job.setCategory(job.getCategory());

            SubJob subJob = new SubJob();
            subJob.setId(subJobId);
            subJob.setJob(job);
            subJob.setNeedsRoom(true);
            subJob.setDate(LocalDate.now());
            subJob.setStartTime(LocalTime.of(10, 0));
            subJob.setExpectedEndTime(LocalTime.of(12, 0));

            when(subJobRepository.existsRoomConflictExceptId(
                    job.getCategory(), subJob.getDate(), subJob.getStartTime(),
                    subJob.getExpectedEndTime(), subJobId
            )).thenReturn(true);

            IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> useCase.execute(subJob));

            assertTrue(exception.getMessage().contains("Há uma sala já em uso"));
            verify(subJobRepository, never()).save(any());
        }
    }
}