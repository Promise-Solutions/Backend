package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.enums.JobCategory;
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

        public Map<JobCategory, Map<String, Double>> getGeneralStats() {
                Map<JobCategory, Map<String, Double>> stats = new HashMap<>();

                for (JobCategory category : JobCategory.values()) {
                        List<SubJob> subJobs = subJobRepository.findAll()
                                        .stream()
                                        .filter(subJob -> subJob.getJob().getCategory() == category
                                                        && subJob.getStatus() == Status.CLOSED)
                                        .toList();

                        double totalValue = subJobs.stream().mapToDouble(SubJob::getValue).sum();
                        double frequency = subJobs.size();

                        stats.put(category, Map.of("totalValue", totalValue, "frequency", frequency));
                }

                return stats;
        }

        public Map<String, Double> getClientStats(UUID clientId) {

                double frequency = 0.0;
                double totalValue = 0.0;
                Double totalCommandsValue = 0.0;

                for (JobCategory category : JobCategory.values()) {
                        List<SubJob> subJobs = subJobRepository.findAll()
                                        .stream()
                                        .filter(subJob -> subJob.getJob().getClient().getId().equals(clientId) &&
                                                        subJob.getJob().getCategory() == category &&
                                                        subJob.getStatus() == Status.CLOSED)
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
                                "totalCommandsValue", totalCommandsValue);
        }

        public Map<String, Double> getBarFinances() {
                double totalOpenCommands = commandRepository.findAll()
                                .stream()
                                .filter(command -> command.getStatus() == Status.OPEN)
                                .mapToDouble(Command::getTotalValue)
                                .sum();

                double totalClosedCommands = commandRepository.findAll()
                                .stream()
                                .filter(command -> command.getStatus() == Status.CLOSED)
                                .mapToDouble(Command::getTotalValue)
                                .sum();

                double finOut = productRepository.findAll()
                                .stream()
                                .mapToDouble(product -> product.getBuyValue())
                                .sum();

                return Map.of(
                                "totalOpenCommands", totalOpenCommands,
                                "totalClosedCommands", totalClosedCommands,
                                "finOut", finOut);
        }
}
