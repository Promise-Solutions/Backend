package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.application.enums.*;
import com.studiozero.projeto.domain.entities.*;
import com.studiozero.projeto.infrastructure.entities.*;
import com.studiozero.projeto.infrastructure.mappers.*;
import com.studiozero.projeto.infrastructure.repositories.Implements.DashboardRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DashboardRepositoryTest {

    private JpaCommandRepository commandRepository;
    private JpaSubJobRepository subJobRepository;
    private JpaClientRepository clientRepository;
    private JpaJobRepository jobRepository;
    private JpaTracingRepository tracingRepository;
    private JpaExpenseRepository expenseRepository;
    private DashboardRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        commandRepository = mock(JpaCommandRepository.class);
        subJobRepository = mock(JpaSubJobRepository.class);
        clientRepository = mock(JpaClientRepository.class);
        jobRepository = mock(JpaJobRepository.class);
        tracingRepository = mock(JpaTracingRepository.class);
        expenseRepository = mock(JpaExpenseRepository.class);
        repository = new DashboardRepositoryImpl(commandRepository, subJobRepository, clientRepository, jobRepository, tracingRepository, expenseRepository);
    }

    @Test
    void testGetDashboardData() {
        assertNull(repository.getDashboardData());
    }

    @Test
    void testGetClientStats() {
        UUID clientId = UUID.randomUUID();

        SubJobEntity subJobEntity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);
        Job job = mock(Job.class);
        Client client = mock(Client.class);

        when(subJobRepository.findAll()).thenReturn(List.of(subJobEntity));
        try (MockedStatic<SubJobEntityMapper> subJobMapper = mockStatic(SubJobEntityMapper.class);
             MockedStatic<CommandEntityMapper> commandMapper = mockStatic(CommandEntityMapper.class)) {

            subJobMapper.when(() -> SubJobEntityMapper.toDomainList(anyList())).thenReturn(List.of(subJob));
            when(subJob.getJob()).thenReturn(job);
            when(job.getClient()).thenReturn(client);
            when(client.getId()).thenReturn(clientId);
            when(subJob.getStatus()).thenReturn(Status.CLOSED);
            when(subJob.getNeedsRoom()).thenReturn(true);
            when(subJob.getValue()).thenReturn(100.0);

            CommandEntity commandEntity = mock(CommandEntity.class);
            Command command = mock(Command.class);
            when(commandRepository.findAllByClientIdAndStatus(clientId, Status.CLOSED)).thenReturn(List.of(commandEntity));
            commandMapper.when(() -> CommandEntityMapper.toDomainList(anyList())).thenReturn(List.of(command));
            when(command.getTotalValue()).thenReturn(200.0);

            Map<String, Double> result = repository.getClientStats(clientId.toString());
            assertEquals(1.0, result.get("frequency"));
            assertEquals(100.0, result.get("totalValue"));
            assertEquals(200.0, result.get("totalCommandsValue"));
            assertEquals(300.0, result.get("ticket"));
        }
    }

    @Test
    void testGetFrequencys() {
        SubJobEntity subJobEntity = mock(SubJobEntity.class);
        SubJob subJob = mock(SubJob.class);
        Job job = mock(Job.class);

        when(subJobRepository.findAll()).thenReturn(List.of(subJobEntity));
        try (MockedStatic<SubJobEntityMapper> subJobMapper = mockStatic(SubJobEntityMapper.class)) {
            subJobMapper.when(() -> SubJobEntityMapper.toDomainList(anyList())).thenReturn(List.of(subJob));
            when(subJob.getStatus()).thenReturn(Status.CLOSED);
            when(subJob.getJob()).thenReturn(job);
            when(job.getServiceType()).thenReturn(JobType.SINGLE);
            when(job.getCategory()).thenReturn(JobCategory.PODCAST);

            Map<String, Double> result = repository.getFrequencys();
            assertEquals(1.0, result.get("frequencySingle"));
            assertEquals(0.0, result.get("frequencyMonthly"));
            assertEquals(1.0, result.get("frequencyByPc"));
            assertEquals(0.0, result.get("frequencyByMr"));
            assertEquals(0.0, result.get("frequencyByPv"));
        }
    }

    @Test
    void testGetActives() {
        when(clientRepository.countByActiveTrueAndClientType(ClientType.SINGLE)).thenReturn(2L);
        when(clientRepository.countByActiveTrueAndClientType(ClientType.MONTHLY)).thenReturn(3L);

        Map<String, Double> result = repository.getActives();
        assertEquals(2.0, result.get("single"));
        assertEquals(3.0, result.get("monthly"));
    }

    @Test
    void testGetBarFinances() {
        CommandEntity commandEntity = mock(CommandEntity.class);
        Command command = mock(Command.class);
        ExpenseEntity expenseEntity = mock(ExpenseEntity.class);
        Expense expense = mock(Expense.class);

        when(commandRepository.findAll()).thenReturn(List.of(commandEntity));
        when(expenseRepository.findAll()).thenReturn(List.of(expenseEntity));
        try (MockedStatic<CommandEntityMapper> commandMapper = mockStatic(CommandEntityMapper.class);
             MockedStatic<ExpenseEntityMapper> expenseMapper = mockStatic(ExpenseEntityMapper.class)) {
            commandMapper.when(() -> CommandEntityMapper.toDomainList(anyList())).thenReturn(List.of(command));
            when(command.getStatus()).thenReturn(Status.CLOSED);
            when(command.getTotalValue()).thenReturn(500.0);

            expenseMapper.when(() -> ExpenseEntityMapper.toDomainList(anyList())).thenReturn(List.of(expense));
            when(expense.getExpenseCategory()).thenReturn(ExpenseCategory.STOCK);
            when(expense.getAmountSpend()).thenReturn(100.0);

            Map<String, Double> result = repository.getBarFinances();
            assertEquals(500.0, result.get("totalEntryValue"));
            assertEquals(100.0, result.get("totalExpenseValue"));
            assertEquals(400.0, result.get("profitOrLoss"));
        }
    }

    @Test
    void testGetCategoryBalances() {
        when(jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PODCAST, Status.CLOSED)).thenReturn(100.0);
        when(jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PHOTO_VIDEO_STUDIO, Status.CLOSED)).thenReturn(200.0);
        when(jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.MUSIC_REHEARSAL, Status.CLOSED)).thenReturn(300.0);

        Map<String, Double> result = repository.getCategoryBalances();
        assertEquals(100.0, result.get("podcastBalance"));
        assertEquals(200.0, result.get("photoVideoStudioBalance"));
        assertEquals(300.0, result.get("musicRehearsalBalance"));
    }

    @Test
    void testGetBalances() {
        CommandEntity commandEntity = mock(CommandEntity.class);
        Command command = mock(Command.class);
        JobEntity jobEntity = mock(JobEntity.class);
        Job job = mock(Job.class);
        ExpenseEntity expenseEntity = mock(ExpenseEntity.class);
        Expense expense = mock(Expense.class);

        when(commandRepository.findAll()).thenReturn(List.of(commandEntity));
        when(jobRepository.findAll()).thenReturn(List.of(jobEntity));
        when(expenseRepository.findAll()).thenReturn(List.of(expenseEntity));
        try (MockedStatic<CommandEntityMapper> commandMapper = mockStatic(CommandEntityMapper.class);
             MockedStatic<JobEntityMapper> jobMapper = mockStatic(JobEntityMapper.class);
             MockedStatic<ExpenseEntityMapper> expenseMapper = mockStatic(ExpenseEntityMapper.class)) {
            commandMapper.when(() -> CommandEntityMapper.toDomainList(anyList())).thenReturn(List.of(command));
            when(command.getStatus()).thenReturn(Status.CLOSED);
            when(command.getTotalValue()).thenReturn(100.0);

            jobMapper.when(() -> JobEntityMapper.toDomainList(anyList())).thenReturn(List.of(job));
            when(job.getStatus()).thenReturn(Status.CLOSED);
            when(job.getTotalValue()).thenReturn(200.0);

            expenseMapper.when(() -> ExpenseEntityMapper.toDomainList(anyList())).thenReturn(List.of(expense));
            when(expense.getAmountSpend()).thenReturn(50.0);

            Map<String, Double> result = repository.getBalances();
            assertEquals(300.0, result.get("totalEntryValue"));
            assertEquals(50.0, result.get("totalExpenseValue"));
            assertEquals(250.0, result.get("profitOrLoss"));
        }
    }

    @Test
    void testGetRecentTime() {
        TracingEntity tracingEntity = mock(TracingEntity.class);
        Tracing tracing = mock(Tracing.class);
        LocalDateTime dateTime = LocalDateTime.now();

        when(tracingRepository.findTopByOrderByDateTimeDesc()).thenReturn(tracingEntity);
        try (MockedStatic<TracingEntityMapper> tracingMapper = mockStatic(TracingEntityMapper.class)) {
            tracingMapper.when(() -> TracingEntityMapper.toDomain(tracingEntity)).thenReturn(tracing);
            when(tracing.getDateTime()).thenReturn(dateTime);

            LocalDateTime result = repository.getRecentTime();
            assertEquals(dateTime, result);
        }
    }
}