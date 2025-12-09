package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.application.usecases.appointment.AppointmentsFoundDto;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import com.studiozero.projeto.infrastructure.entities.TaskEntity;
import com.studiozero.projeto.infrastructure.mappers.SubJobEntityMapper;
import com.studiozero.projeto.infrastructure.mappers.TaskEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.AppointmentRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaSubJobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentRepositoryTest {

    private JpaSubJobRepository subJobRepository;
    private JpaTaskRepository taskRepository;
    private AppointmentRepositoryImpl appointmentRepository;

    @BeforeEach
    void setUp() {
        subJobRepository = mock(JpaSubJobRepository.class);
        taskRepository = mock(JpaTaskRepository.class);
        appointmentRepository = new AppointmentRepositoryImpl(subJobRepository, taskRepository);
    }

    @Test
    void testFindAppointmentsOfTheMonth() {
        int year = 2024;
        int month = 5;
        LocalDate initialDate = LocalDate.of(year, month, 1);
        LocalDate finalDate = LocalDate.of(year, month, 31);

        SubJobEntity subJobEntity = mock(SubJobEntity.class);
        TaskEntity taskEntity = mock(TaskEntity.class);
        SubJob subJob = mock(SubJob.class);
        Task task = mock(Task.class);

        List<SubJobEntity> subJobEntities = List.of(subJobEntity);
        List<TaskEntity> taskEntities = List.of(taskEntity);
        List<SubJob> subJobs = List.of(subJob);
        List<Task> tasks = List.of(task);

        when(subJobRepository.findAllByDateBetween(initialDate, finalDate)).thenReturn(subJobEntities);
        when(taskRepository.findAllByLimitDateBetween(initialDate, finalDate)).thenReturn(taskEntities);

        try (
                MockedStatic<SubJobEntityMapper> subJobMapperMock = mockStatic(SubJobEntityMapper.class);
                MockedStatic<TaskEntityMapper> taskMapperMock = mockStatic(TaskEntityMapper.class)
        ) {
            subJobMapperMock.when(() -> SubJobEntityMapper.toDomainList(subJobEntities)).thenReturn(subJobs);
            taskMapperMock.when(() -> TaskEntityMapper.toDomainList(taskEntities)).thenReturn(tasks);

            AppointmentsFoundDto result = appointmentRepository.findAppointmentsOfTheMonth(year, month);

            assertNotNull(result);
            assertEquals(subJobs, result.subJobs());
            assertEquals(tasks, result.tasks());
        }
    }
}