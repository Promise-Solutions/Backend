package com.studiozero.projeto.application.services;

import com.studiozero.projeto.application.enums.*;
import com.studiozero.projeto.domain.entities.*;
import com.studiozero.projeto.domain.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

        private final CommandRepository commandRepository;
        private final SubJobRepository subJobRepository;
        private final ClientRepository clientRepository;
        private final JobRepository jobRepository;
        private final TracingRepository tracingRepository;
        private final ExpenseRepository expenseRepository;

        public Map<String, Double> getClientStats(UUID clientId) {

                double frequency = 0.0;
                double totalValue = 0.0;
                double totalCommandsValue = 0.0;

                for (JobCategory category : JobCategory.values()) {
                        List<SubJob> subJobs = subJobRepository.findAll()
                                        .stream()
                                        .filter(subJob -> subJob.getJob().getClient().getId().equals(clientId) &&
                                                        subJob.getStatus() == Status.CLOSED && subJob.getNeedsRoom())
                                        .toList();

                        totalValue = subJobs.stream().mapToDouble(SubJob::getValue).sum();
                        frequency = subJobs.size();
                }

                List<Command> closedCommands = commandRepository.findAllByClient_IdAndStatus(clientId, Status.CLOSED);

                totalCommandsValue = closedCommands.stream()
                                .mapToDouble(Command::getTotalValue)
                                .sum();

                Double ticket = (totalValue + totalCommandsValue) / frequency;

                return Map.of(
                                "frequency", frequency,
                                "totalValue", totalValue,
                                "totalCommandsValue", totalCommandsValue,
                                "ticket", ticket
                );
        }

        public Map<String, Double> getFrequencys() {
                double frequencySingle = 0.0;
                double frequencyMonthly = 0.0;
                double frequencyByPc = 0.0;
                double frequencyByMr = 0.0;
                double frequencyByPv = 0.0;

                List<SubJob> subJobs = subJobRepository.findAll()
                        .stream()
                        .filter(subJob -> subJob.getStatus() == Status.CLOSED)
                        .toList();

                for (JobType type : JobType.values()) {
                        frequencySingle = subJobs.stream()
                                .filter(subJob -> subJob.getJob().getServiceType() == JobType.SINGLE)
                                .count();

                        frequencyMonthly = subJobs.stream()
                                .filter(subJob -> subJob.getJob().getServiceType() == JobType.MONTHLY)
                                .count();

                }

                for (JobCategory category : JobCategory.values()) {
                        frequencyByPc = subJobs.stream()
                                .filter(subJob -> subJob.getJob().getCategory() == JobCategory.PODCAST)
                                .count();

                        frequencyByMr = subJobs.stream()
                                .filter(subJob -> subJob.getJob().getCategory() == JobCategory.MUSIC_REHEARSAL)
                                .count();

                        frequencyByPv = subJobs.stream()
                                .filter(subJob -> subJob.getJob().getCategory() == JobCategory.PHOTO_VIDEO_STUDIO)
                                .count();
                }

                return Map.of(
                        "frequencySingle", frequencySingle,
                        "frequencyMonthly", frequencyMonthly,
                        "frequencyByPc", frequencyByPc,
                        "frequencyByMr", frequencyByMr,
                        "frequencyByPv", frequencyByPv
                );
        }

        public Map<String, Double> getActives() {
                double single = clientRepository.countByActiveTrueAndClientType(ClientType.SINGLE);
                double monthly = clientRepository.countByActiveTrueAndClientType(ClientType.MONTHLY);
                return Map.of(
                        "monthly", monthly,
                        "single", single
                );

        }

        public Map<String, Double> getBarFinances() {
                List<Command> commandList = commandRepository.findAll();
                List<Expense> expenseList = expenseRepository.findAll();
                double totalClosedCommands = 0.0;
                double totalExpenseProduct = 0.0;
                double profitOrLoss = 0.0;

                totalClosedCommands = commandList
                                .stream()
                                .filter(command -> command.getStatus() == Status.CLOSED)
                                .mapToDouble(Command::getTotalValue)
                                .sum();

                totalExpenseProduct = expenseList
                        .stream()
                        .filter(expense -> expense.getExpenseCategory() == ExpenseCategory.STOCK)
                        .mapToDouble(Expense::getAmountSpend)
                        .sum();

                profitOrLoss = totalClosedCommands - totalExpenseProduct;

                return Map.of(
                        "totalEntryValue", totalClosedCommands,
                        "totalExpenseValue", totalExpenseProduct,
                        "profitOrLoss", profitOrLoss
                );
        }

        public Map<String, Double> getCategoryBalances() {
                double podcastBalance = Optional.ofNullable(
                        jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PODCAST, Status.CLOSED)
                        ).orElse(0.0);
                double photoVideoStudioBalance = Optional.ofNullable(
                        jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PHOTO_VIDEO_STUDIO, Status.CLOSED)
                        ).orElse(0.0);
                double musicRehearsalBalance = Optional.ofNullable(
                        jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.MUSIC_REHEARSAL, Status.CLOSED)
                        ).orElse(0.0);

                return Map.of(
                        "podcastBalance", podcastBalance,
                        "photoVideoStudioBalance", photoVideoStudioBalance,
                        "musicRehearsalBalance", musicRehearsalBalance
                );
        }

        public Map<String, Double> getBalances() {
                List<Command> commandList = commandRepository.findAll();
                List<Job> jobList = jobRepository.findAll();
                List<Expense> expenseList = expenseRepository.findAll();
                double totalCommandEntryValue = 0.0;
                double totalJobEntryValue = 0.0;
                double totalExpenseValue = 0.0;

                totalCommandEntryValue = commandList
                        .stream()
                        .filter(command -> command.getStatus() == Status.CLOSED)
                        .mapToDouble(Command::getTotalValue)
                        .sum();

                totalJobEntryValue = jobList
                        .stream()
                        .filter(job -> job.getStatus() == Status.CLOSED)
                        .mapToDouble(Job::getTotalValue)
                        .sum();

                totalExpenseValue = expenseList
                        .stream()
                        .mapToDouble(Expense::getAmountSpend)
                        .sum();

                double totalEntryValue = totalCommandEntryValue + totalJobEntryValue;

                double profitOrLoss =  totalEntryValue - totalExpenseValue;

                return Map.of(
                        "totalEntryValue", totalEntryValue,
                        "totalExpenseValue", totalExpenseValue,
                        "profitOrLoss", profitOrLoss
                );
        }

        public LocalDateTime getRecentTime() {
                Tracing lastTracing = tracingRepository.findTopByOrderByDateTimeDesc();
                return lastTracing.getDateTime();
        }
}
