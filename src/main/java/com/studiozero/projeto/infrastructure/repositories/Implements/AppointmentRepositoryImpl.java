package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.application.usecases.appointment.AppointmentsFoundDto;
import com.studiozero.projeto.domain.repositories.AppointmentRepository;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import com.studiozero.projeto.infrastructure.entities.TaskEntity;
import com.studiozero.projeto.infrastructure.mappers.SubJobEntityMapper;
import com.studiozero.projeto.infrastructure.mappers.TaskEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaSubJobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaTaskRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final JpaSubJobRepository subJobRepository;
    private final JpaTaskRepository taskRepository;

    public AppointmentRepositoryImpl(JpaSubJobRepository subJobRepository, JpaTaskRepository taskRepository) {
        this.subJobRepository = subJobRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public AppointmentsFoundDto findAppointmentsOfTheMonth(int year, int month) {
        int firstDayOfMonth = 1;
        int lastDayOfMonth = Month.of(month).minLength();

        LocalDate initialDate = LocalDate.of(year, month, firstDayOfMonth);
        LocalDate finalDate = LocalDate.of(year, month, lastDayOfMonth);

        List<SubJobEntity> subJobsFound = subJobRepository.findAllByDateBetween(initialDate, finalDate);
        List<TaskEntity> tasksFound = taskRepository.findAllByLimitDateBetween(initialDate, finalDate);

        AppointmentsFoundDto appointments = new AppointmentsFoundDto(
                SubJobEntityMapper.toDomainList(subJobsFound),
                TaskEntityMapper.toDomainList(tasksFound)
        );
        return appointments;
    }
}
