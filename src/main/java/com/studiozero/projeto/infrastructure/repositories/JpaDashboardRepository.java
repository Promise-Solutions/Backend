package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.repositories.DashboardRepository;
import com.studiozero.projeto.domain.entities.*;
import com.studiozero.projeto.application.enums.*;
import com.studiozero.projeto.infrastructure.entities.*;
import com.studiozero.projeto.infrastructure.mappers.*;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaDashboardRepository implements DashboardRepository {
    private final JpaCommandRepository commandRepository;
    private final JpaSubJobRepository subJobRepository;
    private final JpaClientRepository clientRepository;
    private final JpaJobRepository jobRepository;
    private final JpaTracingRepository tracingRepository;
    private final JpaExpenseRepository expenseRepository;

    @Override
    public Object getDashboardData() {
        // Implementação customizada se necessário
        return null;
    }

    @Override
    public Map<String, Double> getClientStats(Object clientId) {
        UUID clientUUID = UUID.fromString(clientId.toString());
        double frequency = 0.0;
        double totalValue = 0.0;
        double totalCommandsValue = 0.0;

        List<SubJobEntity> subJobEntities = subJobRepository.findAll();
        List<SubJob> subJobs = SubJobEntityMapper.toDomainList(subJobEntities);
        List<SubJob> filteredSubJobs = subJobs.stream()
                .filter(subJob -> subJob.getJob().getClient().getId().equals(clientUUID) &&
                        subJob.getStatus() == Status.CLOSED && subJob.getNeedsRoom())
                .toList();
        totalValue = filteredSubJobs.stream().mapToDouble(SubJob::getValue).sum();
        frequency = filteredSubJobs.size();

        List<CommandEntity> commandEntities = commandRepository.findAllByClientIdAndStatus(clientUUID, Status.CLOSED);
        List<Command> closedCommands = CommandEntityMapper.toDomainList(commandEntities);
        totalCommandsValue = closedCommands.stream().mapToDouble(Command::getTotalValue).sum();
        Double ticket = (frequency > 0) ? (totalValue + totalCommandsValue) / frequency : 0.0;
        return Map.of(
                "frequency", frequency,
                "totalValue", totalValue,
                "totalCommandsValue", totalCommandsValue,
                "ticket", ticket);
    }

    @Override
    public Map<String, Double> getFrequencys() {
        List<SubJobEntity> subJobEntities = subJobRepository.findAll();
        List<SubJob> subJobs = SubJobEntityMapper.toDomainList(subJobEntities);
        List<SubJob> closedSubJobs = subJobs.stream().filter(subJob -> subJob.getStatus() == Status.CLOSED).toList();
        double frequencySingle = closedSubJobs.stream().filter(subJob -> subJob.getJob().getServiceType() == JobType.SINGLE).count();
        double frequencyMonthly = closedSubJobs.stream().filter(subJob -> subJob.getJob().getServiceType() == JobType.MONTHLY).count();
        double frequencyByPc = closedSubJobs.stream().filter(subJob -> subJob.getJob().getCategory() == JobCategory.PODCAST).count();
        double frequencyByMr = closedSubJobs.stream().filter(subJob -> subJob.getJob().getCategory() == JobCategory.MUSIC_REHEARSAL).count();
        double frequencyByPv = closedSubJobs.stream().filter(subJob -> subJob.getJob().getCategory() == JobCategory.PHOTO_VIDEO_STUDIO).count();
        return Map.of(
                "frequencySingle", frequencySingle,
                "frequencyMonthly", frequencyMonthly,
                "frequencyByPc", frequencyByPc,
                "frequencyByMr", frequencyByMr,
                "frequencyByPv", frequencyByPv);
    }

    @Override
    public Map<String, Double> getActives() {
        long single = clientRepository.countByActiveTrueAndClientType(ClientType.SINGLE);
        long monthly = clientRepository.countByActiveTrueAndClientType(ClientType.MONTHLY);
        return Map.of(
                "monthly", (double) monthly,
                "single", (double) single);
    }

    @Override
    public Map<String, Double> getBarFinances() {
        List<CommandEntity> commandEntities = commandRepository.findAll();
        List<Command> commandList = CommandEntityMapper.toDomainList(commandEntities);
        List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
        List<Expense> expenseList = ExpenseEntityMapper.toDomainList(expenseEntities);
        double totalClosedCommands = commandList.stream().filter(command -> command.getStatus() == Status.CLOSED)
                .mapToDouble(Command::getTotalValue).sum();
        double totalExpenseProduct = expenseList.stream()
                .filter(expense -> expense.getExpenseCategory() == ExpenseCategory.STOCK)
                .mapToDouble(Expense::getAmountSpend).sum();
        double profitOrLoss = totalClosedCommands - totalExpenseProduct;
        return Map.of(
                "totalEntryValue", totalClosedCommands,
                "totalExpenseValue", totalExpenseProduct,
                "profitOrLoss", profitOrLoss);
    }

    @Override
    public Map<String, Double> getCategoryBalances() {
        double podcastBalance = Optional.ofNullable(
                jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PODCAST, Status.CLOSED)).orElse(0.0);
        double photoVideoStudioBalance = Optional.ofNullable(
                jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PHOTO_VIDEO_STUDIO, Status.CLOSED))
                .orElse(0.0);
        double musicRehearsalBalance = Optional.ofNullable(
                jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.MUSIC_REHEARSAL, Status.CLOSED)).orElse(0.0);
        return Map.of(
                "podcastBalance", podcastBalance,
                "photoVideoStudioBalance", photoVideoStudioBalance,
                "musicRehearsalBalance", musicRehearsalBalance);
    }

    @Override
    public Map<String, Double> getBalances() {
        List<CommandEntity> commandEntities = commandRepository.findAll();
        List<Command> commandList = CommandEntityMapper.toDomainList(commandEntities);
        List<JobEntity> jobEntities = jobRepository.findAll();
        List<Job> jobList = JobEntityMapper.toDomainList(jobEntities);
        List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
        List<Expense> expenseList = ExpenseEntityMapper.toDomainList(expenseEntities);
        double totalCommandEntryValue = commandList.stream().filter(command -> command.getStatus() == Status.CLOSED)
                .mapToDouble(Command::getTotalValue).sum();
        double totalJobEntryValue = jobList.stream().filter(job -> job.getStatus() == Status.CLOSED)
                .mapToDouble(Job::getTotalValue).sum();
        double totalExpenseValue = expenseList.stream().mapToDouble(Expense::getAmountSpend).sum();
        double totalEntryValue = totalCommandEntryValue + totalJobEntryValue;
        double profitOrLoss = totalEntryValue - totalExpenseValue;
        return Map.of(
                "totalEntryValue", totalEntryValue,
                "totalExpenseValue", totalExpenseValue,
                "profitOrLoss", profitOrLoss);
    }

    @Override
    public java.time.LocalDateTime getRecentTime() {
        TracingEntity lastTracingEntity = tracingRepository.findTopByOrderByDateTimeDesc();
        Tracing lastTracing = TracingEntityMapper.toDomain(lastTracingEntity);
        return lastTracing != null ? lastTracing.getDateTime() : null;
    }
}
