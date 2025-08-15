package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.JobService;
import com.studiozero.projeto.application.services.SubJobService;
import com.studiozero.projeto.application.services.TracingService;
import com.studiozero.projeto.application.dtos.response.SubJobDeleteResponseDTO;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Context;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.infrastructure.exceptions.ConflictException;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.SubJobRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubJobServiceTest {

    @InjectMocks
    private SubJobService subJobService;

    @Mock
    private SubJobRepository subJobRepository;

    @Mock
    private JobService jobService;

    @Mock
    private TracingService tracingService;

    private SubJob subJob;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Job job = new Job();
        job.setId(UUID.randomUUID());
        job.setCategory(JobCategory.PHOTO_VIDEO_STUDIO);

        subJob = new SubJob();
        subJob.setId(UUID.randomUUID());
        subJob.setJob(job);
        subJob.setDate(LocalDate.now());
        subJob.setStartTime(LocalTime.of(10, 0));
        subJob.setExpectedEndTime(LocalTime.of(11, 0));
        subJob.setNeedsRoom(true);
        subJob.setStatus(Status.COMPLETED);
    }

    @Test
    @DisplayName("Should create sub job successfully when no room conflict exists")
    void createSubJob_Success() {
        when(subJobRepository.existsRoomConflict(any(), any(), any(), any())).thenReturn(false);
        when(subJobRepository.save(any(SubJob.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(tracingService.setTracing(Context.JOB)).thenReturn(null);

        SubJob created = subJobService.createSubJob(subJob);

        assertNotNull(created.getId());
        verify(subJobRepository).save(any());
        verify(tracingService).setTracing(Context.JOB);
    }

    @Test
    @DisplayName("Should throw ConflictException when creating sub job with room conflict")
    void createSubJob_Conflict() {
        when(subJobRepository.existsRoomConflict(any(), any(), any(), any())).thenReturn(true);

        assertThrows(ConflictException.class, () -> subJobService.createSubJob(subJob));

        verify(subJobRepository, never()).save(any());
        verify(tracingService, never()).setTracing(any());
    }

    @Test
    @DisplayName("Should find sub job by ID successfully")
    void findSubJobById_Success() {
        when(subJobRepository.findById(subJob.getId())).thenReturn(Optional.of(subJob));

        SubJob found = subJobService.findSubJobById(subJob.getId());

        assertEquals(subJob, found);
    }

    @Test
    @DisplayName("Should throw NotFoundException when sub job ID does not exist")
    void findSubJobById_NotFound() {
        when(subJobRepository.findById(subJob.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> subJobService.findSubJobById(subJob.getId()));
    }

    @Test
    @DisplayName("Should list all sub jobs")
    void listSubJobs_ReturnsList() {
        List<SubJob> subJobs = List.of(subJob, subJob);
        when(subJobRepository.findAll()).thenReturn(subJobs);

        List<SubJob> result = subJobService.listSubJobs();

        assertEquals(2, result.size());
        assertEquals(subJobs, result);
    }

    @Test
    @DisplayName("Should update sub job successfully when no conflict exists")
    void updateSubJob_Success() {
        when(subJobRepository.existsById(subJob.getId())).thenReturn(true);
        when(subJobRepository.existsRoomConflict(any(), any(), any(), any(), any())).thenReturn(false);
        when(subJobRepository.save(any())).thenReturn(subJob);
        when(tracingService.setTracing(Context.JOB)).thenReturn(null);

        SubJob updated = subJobService.updateSubJob(subJob);

        assertEquals(subJob.getId(), updated.getId());
        verify(subJobRepository).save(subJob);
        verify(tracingService).setTracing(Context.JOB);
    }

    @Test
    @DisplayName("Should throw ConflictException when updating sub job with room conflict")
    void updateSubJob_Conflict() {
        when(subJobRepository.existsById(subJob.getId())).thenReturn(true);
        when(subJobRepository.existsRoomConflict(any(), any(), any(), any(), any())).thenReturn(true);

        assertThrows(ConflictException.class, () -> subJobService.updateSubJob(subJob));
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existing sub job")
    void updateSubJob_NotFound() {
        when(subJobRepository.existsById(subJob.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> subJobService.updateSubJob(subJob));
    }

    @Test
    @DisplayName("Should update sub job status successfully")
    void updateSubJobStatus_Success() {
        when(subJobRepository.findById(subJob.getId())).thenReturn(Optional.of(subJob));
        when(subJobRepository.save(any())).thenReturn(subJob);
        when(tracingService.setTracing(Context.JOB)).thenReturn(null);

        SubJob updated = subJobService.updateSubJobStatus(subJob.getId(), Status.COMPLETED);

        assertEquals(Status.COMPLETED, updated.getStatus());
        verify(tracingService).setTracing(Context.JOB);
        verify(subJobRepository).save(subJob);
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating status of non-existing sub job")
    void updateSubJobStatus_NotFound() {
        when(subJobRepository.findById(subJob.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> subJobService.updateSubJobStatus(subJob.getId(), Status.COMPLETED));
    }

    @Test
    @DisplayName("Should list sub jobs by FK service")
    void listSubJobsByFkService_ReturnsList() {
        UUID fkServiceId = UUID.randomUUID();
        List<SubJob> subJobs = List.of(subJob);
        when(subJobRepository.findAllByJob_Id(fkServiceId)).thenReturn(subJobs);

        List<SubJob> result = subJobService.listSubJobsByFkService(fkServiceId);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should delete sub job successfully and return response DTO")
    void deleteSubJob_Success() {
        UUID id = subJob.getId();
        when(subJobRepository.findById(id)).thenReturn(Optional.of(subJob));
        when(tracingService.setTracing(Context.JOB)).thenReturn(null);
        when(jobService.evaluateJobStatus(subJob.getJob().getId())).thenReturn(Status.COMPLETED);
        when(jobService.calculateTotalValue(subJob.getJob())).thenReturn(100.0);

        SubJobDeleteResponseDTO response = subJobService.deleteSubJob(id);

        assertEquals(id, response.getId());
        assertEquals(Status.COMPLETED, response.getJobStatus());
        assertEquals(100.0, response.getJobTotalValue());
        verify(subJobRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existing sub job")
    void deleteSubJob_NotFound() {
        UUID id = subJob.getId();
        when(subJobRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> subJobService.deleteSubJob(id));
        verify(subJobRepository, never()).deleteById(any());
    }
}
