package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.EmailService;
import com.studiozero.projeto.application.services.EmployeeService;
import com.studiozero.projeto.application.services.ScheduledTaskService;
import com.studiozero.projeto.application.services.SubJobService;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.SubJob;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduledTaskServiceTest {

    @InjectMocks
    private ScheduledTaskService scheduledTaskService;

    @Mock
    private EmailService emailService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private SubJobService subJobService;

    @Test
    @DisplayName("Should do nothing if no SubJobs are scheduled for today")
    void testExecutar_NoSubJobsForToday() {
        when(subJobService.listSubJobs()).thenReturn(List.of());

        scheduledTaskService.executar();

        verify(emailService, never()).enviarAviso(anyList(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should not send email if no employee has a valid email")
    void testExecutar_NoValidEmployeeEmails() {
        SubJob subJob = new SubJob();
        subJob.setDate(LocalDate.now());

        Employee employee = new Employee();
        employee.setEmail("   "); // inválido

        when(subJobService.listSubJobs()).thenReturn(List.of(subJob));
        when(employeeService.listEmployees()).thenReturn(List.of(employee));

        scheduledTaskService.executar();

        verify(emailService, never()).enviarAviso(anyList(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should send email when there are SubJobs today and valid employee emails")
    void testExecutar_SubJobsTodayWithValidEmails() {
        SubJob subJob = new SubJob();
        subJob.setDate(LocalDate.now());

        Employee employee1 = new Employee();
        employee1.setEmail("teste1@email.com");

        Employee employee2 = new Employee();
        employee2.setEmail("teste2@email.com");

        when(subJobService.listSubJobs()).thenReturn(List.of(subJob));
        when(employeeService.listEmployees()).thenReturn(List.of(employee1, employee2));

        scheduledTaskService.executar();

        verify(emailService).enviarAviso(
                eq(List.of("teste1@email.com", "teste2@email.com")),
                eq("StudioZero: Você tem atendimentos hoje!"),
                eq("Olá! Você tem atendimentos presenciais agendados para hoje. Verifique sua agenda no sistema.")
        );
    }

    @Test
    @DisplayName("Should log error if MailException is thrown during email sending")
    void testExecutar_MailExceptionThrown() {
        SubJob subJob = new SubJob();
        subJob.setDate(LocalDate.now());

        Employee employee = new Employee();
        employee.setEmail("valido@email.com");

        when(subJobService.listSubJobs()).thenReturn(List.of(subJob));
        when(employeeService.listEmployees()).thenReturn(List.of(employee));
        doThrow(new MailException("Erro ao enviar e-mail") {}).when(emailService)
                .enviarAviso(anyList(), anyString(), anyString());

        scheduledTaskService.executar();

        verify(emailService).enviarAviso(anyList(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should log error if unexpected exception is thrown during execution")
    void testExecutar_GenericExceptionThrown() {
        SubJob subJob = new SubJob();
        subJob.setDate(LocalDate.now());

        Employee employee = new Employee();
        employee.setEmail("valido@email.com");

        when(subJobService.listSubJobs()).thenReturn(List.of(subJob));
        when(employeeService.listEmployees()).thenReturn(List.of(employee));
        doThrow(new RuntimeException("Erro genérico")).when(emailService)
                .enviarAviso(anyList(), anyString(), anyString());

        scheduledTaskService.executar();

        verify(emailService).enviarAviso(anyList(), anyString(), anyString());
    }
}
