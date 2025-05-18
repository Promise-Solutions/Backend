package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.ClientType;
import com.studiozero.projeto.enums.JobType;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import com.studiozero.projeto.repositories.ProductRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

        private final CommandRepository commandRepository;
        private final ProductRepository productRepository;
        private final SubJobRepository subJobRepository;
        private final ClientRepository clientRepository;

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
                return Map.of(
                        "monthly", clientRepository.countByActiveTrueAndClientType(ClientType.MONTHLY),
                        "single", clientRepository.countByActiveTrueAndClientType(ClientType.SINGLE)
                );
        }
}
