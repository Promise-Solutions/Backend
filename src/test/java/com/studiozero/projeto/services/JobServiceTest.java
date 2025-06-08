package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.JobRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private SubJobRepository subJobRepository;

    @Mock
    private TracingService tracingService;

    @InjectMocks
    private JobService jobService;

    private UUID jobId;
    private Job job;

    @BeforeEach
    void setUp() {
        jobId = UUID.randomUUID();
        job = new Job();
        job.setId(jobId);
        job.setStatus(Status.PENDING);
        job.setTotalValue(0.0);
    }

    @Test
    @DisplayName("Should create a job successfully and set tracing")
    void testCreateJob() {
        when(jobRepository.save(any())).thenReturn(job);

        Job created = jobService.createJob(job);

        assertNotNull(created);
        verify(tracingService).setTracing(Context.JOB);
        verify(jobRepository).save(any(Job.class));
    }

    @Test
    @DisplayName("Should find job by ID successfully")
    void testFindJobByIdSuccess() {
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        Job found = jobService.findjobById(jobId);

        assertNotNull(found);
        assertEquals(jobId, found.getId());
        verify(tracingService).setTracing(Context.JOB);
    }

    @Test
    @DisplayName("Should throw NotFoundException when job not found by ID")
    void testFindJobByIdNotFound() {
        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> jobService.findjobById(jobId));
    }

    @Test
    @DisplayName("Should list all jobs successfully")
    void testListJobs() {
        List<Job> jobs = List.of(job);
        when(jobRepository.findAll()).thenReturn(jobs);

        List<Job> result = jobService.listJobs();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should update an existing job successfully")
    void testUpdateJobSuccess() {
        when(jobRepository.existsById(jobId)).thenReturn(true);
        when(jobRepository.save(job)).thenReturn(job);

        Job updated = jobService.updateJob(job);

        assertNotNull(updated);
        verify(tracingService).setTracing(Context.JOB);
        verify(jobRepository).save(job);
    }

    @Test
    @DisplayName("Should throw NotFoundException when trying to update a non-existent job")
    void testUpdateJobNotFound() {
        when(jobRepository.existsById(jobId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> jobService.updateJob(job));
    }

    @Test
    @DisplayName("Should delete job successfully when no related subjobs exist")
    void testDeleteJobSuccess() {
        when(subJobRepository.existsByJob_Id(jobId)).thenReturn(false);
        when(jobRepository.existsById(jobId)).thenReturn(true);

        jobService.deleteJob(jobId);

        verify(tracingService).setTracing(Context.JOB);
        verify(jobRepository).deleteById(jobId);
    }

    @Test
    @DisplayName("Should throw ConflictException when trying to delete job with subjobs")
    void testDeleteJobConflict() {
        when(subJobRepository.existsByJob_Id(jobId)).thenReturn(true);

        assertThrows(ConflictException.class, () -> jobService.deleteJob(jobId));
    }

    @Test
    @DisplayName("Should throw NotFoundException when trying to delete non-existent job")
    void testDeleteJobNotFound() {
        when(subJobRepository.existsByJob_Id(jobId)).thenReturn(false);
        when(jobRepository.existsById(jobId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> jobService.deleteJob(jobId));
    }

    @Test
    @DisplayName("Should list jobs by client ID")
    void testListJobsByFkClient() {
        UUID clientId = UUID.randomUUID();
        when(jobRepository.findAllByClient_Id(clientId)).thenReturn(List.of(job));

        List<Job> jobs = jobService.listJobsByFkClient(clientId);

        assertEquals(1, jobs.size());
    }

    @Test
    @DisplayName("Should return PENDING status when no subjobs are found")
    void testEvaluateJobStatusPending() {
        when(subJobRepository.countByJob_Id(jobId)).thenReturn(0);

        Status result = jobService.evaluateJobStatus(jobId);

        assertEquals(Status.PENDING, result);
    }

    @Test
    @DisplayName("Should return CLOSED status when all subjobs are CLOSED")
    void testEvaluateJobStatusClosed() {
        when(subJobRepository.countByJob_Id(jobId)).thenReturn(3);
        when(subJobRepository.countByJob_IdAndStatus(jobId, Status.CLOSED)).thenReturn(3);
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
        when(jobRepository.save(any())).thenReturn(job);

        Status result = jobService.evaluateJobStatus(jobId);

        assertEquals(Status.CLOSED, result);
        verify(tracingService).setTracing(Context.JOB);
    }

    @Test
    @DisplayName("Should return WORKING status when some subjobs are not CLOSED")
    void testEvaluateJobStatusWorking() {
        when(subJobRepository.countByJob_Id(jobId)).thenReturn(3);
        when(subJobRepository.countByJob_IdAndStatus(jobId, Status.CLOSED)).thenReturn(1);
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
        when(jobRepository.save(any())).thenReturn(job);

        Status result = jobService.evaluateJobStatus(jobId);

        assertEquals(Status.WORKING, result);
    }

    @Test
    @DisplayName("Should calculate total value of job from subjobs")
    void testCalculateTotalValue() {
        SubJob subJob1 = new SubJob();
        subJob1.setValue(100.0);
        SubJob subJob2 = new SubJob();
        subJob2.setValue(200.0);
        List<SubJob> subJobs = List.of(subJob1, subJob2);

        when(subJobRepository.findAllByJob_Id(jobId)).thenReturn(subJobs);
        when(jobRepository.save(any())).thenReturn(job);

        Double total = jobService.calculateTotalValue(job);

        assertEquals(300.0, total);
        verify(tracingService).setTracing(Context.JOB);
    }

    @Test
    @DisplayName("Should return 0.0 total value when there are no subjobs")
    void testCalculateTotalValueEmptySubJobs() {
        when(subJobRepository.findAllByJob_Id(jobId)).thenReturn(List.of());
        when(jobRepository.save(any())).thenReturn(job);

        Double total = jobService.calculateTotalValue(job);

        assertEquals(0.0, total);
        verify(jobRepository, times(1)).save(job);
    }
}
