package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.ClientType;
import com.studiozero.projeto.enums.JobType;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

        private final CommandRepository commandRepository;
        private final ProductRepository productRepository;
        private final SubJobRepository subJobRepository;
        private final ClientRepository clientRepository;
        private final JobRepository jobRepository;

        public Map<String, Double> getClientStats(UUID clientId) {

                double frequency = 0.0;
                double totalValue = 0.0;
                Double totalCommandsValue = 0.0;

                for (JobCategory category : JobCategory.values()) {
                        List<SubJob> subJobs = subJobRepository.findAll()
                                        .stream()
                                        .filter(subJob -> subJob.getJob().getClient().getId().equals(clientId) &&
                                                        subJob.getJob().getCategory() == category &&
                                                        subJob.getStatus() == Status.CLOSED && subJob.getNeedsRoom())
                                        .toList();

                        totalValue += subJobs.stream().mapToDouble(SubJob::getValue).sum();
                        frequency += subJobs.size();
                }

                List<Command> closedCommands = commandRepository.findAllByClient_IdAndStatus(clientId, Status.CLOSED);

                totalCommandsValue = closedCommands.stream()
                                .mapToDouble(Command::getTotalValue)
                                .sum();

                return Map.of(
                                "frequency", frequency,
                                "totalValue", totalValue,
                                "totalCommandsValue", totalCommandsValue
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
                        .filter(subJob -> subJob.getStatus() == Status.CLOSED && subJob.getNeedsRoom())
                        .toList();

                for (JobType type : JobType.values()) {
                        frequencySingle += subJobs.stream()
                                .filter(subJob -> subJob.getJob().getServiceType() == type)
                                .count();

                        frequencyMonthly += subJobs.stream()
                                .filter(subJob -> subJob.getJob().getServiceType() == type)
                                .count();

                }

                for (JobCategory category : JobCategory.values()) {
                        frequencyByPc += subJobs.stream()
                                .filter(subJob -> subJob.getJob().getCategory() == category)
                                .count();

                        frequencyByMr += subJobs.stream()
                                .filter(subJob -> subJob.getJob().getCategory() == category)
                                .count();

                        frequencyByPv += subJobs.stream()
                                .filter(subJob -> subJob.getJob().getCategory() == category)
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

        public Map<String, Double> getBalances() {
                double podcastBalance = Optional.ofNullable(
                        jobRepository.sumTotalValueByCategory(JobCategory.PODCAST)
                        ).orElse(0.0);
                double photoVideoStudioBalance = Optional.ofNullable(
                        jobRepository.sumTotalValueByCategory(JobCategory.PHOTO_VIDEO_STUDIO)
                        ).orElse(0.0);
                double musicRehearsalBalance = Optional.ofNullable(
                        jobRepository.sumTotalValueByCategory(JobCategory.MUSIC_REHEARSAL)
                        ).orElse(0.0);

                return Map.of(
                        "podcastBalance", podcastBalance,
                        "photoVideoStudioBalance", photoVideoStudioBalance,
                        "musicRehearsalBalance", musicRehearsalBalance
                );
        }

        public LocalDate getRecentTime() {
                LocalDateTime maxOpening = commandRepository.findMaxOpeningDate();
                LocalDateTime maxClosing = commandRepository.findMaxClosingDate();
                LocalDate maxClient = clientRepository.findMaxCreatedDate();
                LocalDate maxSubJob = subJobRepository.findMaxDate();

                LocalDate maxOpeningDate = (maxOpening != null) ? maxOpening.toLocalDate() : null;
                LocalDate maxClosingDate = (maxClosing != null) ? maxClosing.toLocalDate() : null;

                LocalDate mostRecent = null;

                if (maxOpeningDate != null) {
                        mostRecent = maxOpeningDate;
                }

                if (maxClosingDate != null) {
                        if (mostRecent == null || maxClosingDate.isAfter(mostRecent)) {
                                mostRecent = maxClosingDate;
                        }
                }

                if (maxClient != null) {
                        if (mostRecent == null || maxClient.isAfter(mostRecent)) {
                                mostRecent = maxClient;
                        }
                }

                if (maxSubJob != null) {
                        if (mostRecent == null || maxSubJob.isAfter(mostRecent)) {
                                mostRecent = maxSubJob;
                        }
                }

                return mostRecent;
        }
}
