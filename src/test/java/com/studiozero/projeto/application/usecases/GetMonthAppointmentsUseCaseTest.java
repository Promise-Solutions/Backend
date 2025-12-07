package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.appointment.AppointmentsFoundDto;
import com.studiozero.projeto.application.usecases.appointment.GetMonthAppointmentsUseCase;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.AppointmentRepository;
import com.studiozero.projeto.web.handlers.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetMonthAppointmentsUseCaseTest {

    @Mock
    private AppointmentRepository repository;

    private GetMonthAppointmentsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetMonthAppointmentsUseCase(repository);
    }

    @Test
    void deveRetornarAppointmentsQuandoParametrosValidos() {
        int year = 2024;
        int month = 12;

        SubJob subJob1 = new SubJob();
        SubJob subJob2 = new SubJob();
        Task task1 = new Task();
        Task task2 = new Task();

        AppointmentsFoundDto expectedDto = new AppointmentsFoundDto(
                List.of(subJob1, subJob2),
                List.of(task1, task2)
        );

        when(repository.findAppointmentsOfTheMonth(year, month)).thenReturn(expectedDto);

        AppointmentsFoundDto result = useCase.execute(year, month);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        assertEquals(2, result.subJobs().size());
        assertEquals(2, result.tasks().size());
        verify(repository, times(1)).findAppointmentsOfTheMonth(year, month);
    }

    @Test
    void deveLancarExcecaoQuandoYearForZero() {
        int year = 0;
        int month = 6;

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> useCase.execute(year, month));

        assertEquals("Year has a 0 or negative value", exception.getMessage());
        verify(repository, never()).findAppointmentsOfTheMonth(anyInt(), anyInt());
    }

    @Test
    void deveLancarExcecaoQuandoYearForNegativo() {
        int year = -2024;
        int month = 6;

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> useCase.execute(year, month));

        assertEquals("Year has a 0 or negative value", exception.getMessage());
        verify(repository, never()).findAppointmentsOfTheMonth(anyInt(), anyInt());
    }

    @Test
    void deveLancarExcecaoQuandoMonthForMaiorQue12() {
        int year = 2024;
        int month = 13;

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> useCase.execute(year, month));

        assertEquals("Month has a impossible value, it must be between 1 and 12", exception.getMessage());
        verify(repository, never()).findAppointmentsOfTheMonth(anyInt(), anyInt());
    }

    @Test
    void deveLancarExcecaoQuandoMonthForZero() {
        int year = 2024;
        int month = 0;

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> useCase.execute(year, month));

        assertEquals("Month has a impossible value, it must be between 1 and 12", exception.getMessage());
        verify(repository, never()).findAppointmentsOfTheMonth(anyInt(), anyInt());
    }

    @Test
    void deveLancarExcecaoQuandoMonthForNegativo() {
        int year = 2024;
        int month = -1;

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> useCase.execute(year, month));

        assertEquals("Month has a impossible value, it must be between 1 and 12", exception.getMessage());
        verify(repository, never()).findAppointmentsOfTheMonth(anyInt(), anyInt());
    }

    @Test
    void deveAceitarMonth1() {
        int year = 2024;
        int month = 1;
        AppointmentsFoundDto expectedDto = new AppointmentsFoundDto(List.of(), List.of());
        when(repository.findAppointmentsOfTheMonth(year, month)).thenReturn(expectedDto);

        AppointmentsFoundDto result = useCase.execute(year, month);

        assertNotNull(result);
        verify(repository, times(1)).findAppointmentsOfTheMonth(year, month);
    }

    @Test
    void deveAceitarMonth12() {
        int year = 2024;
        int month = 12;
        AppointmentsFoundDto expectedDto = new AppointmentsFoundDto(List.of(), List.of());
        when(repository.findAppointmentsOfTheMonth(year, month)).thenReturn(expectedDto);

        AppointmentsFoundDto result = useCase.execute(year, month);

        assertNotNull(result);
        verify(repository, times(1)).findAppointmentsOfTheMonth(year, month);
    }
}