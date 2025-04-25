package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.response.DashboardResponseDto;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.repositories.CommandRepository;
import com.studiozero.projeto.repositories.JobRepository;
import com.studiozero.projeto.repositories.ProductRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import com.studiozero.projeto.specifications.CommandSpecifications;
import com.studiozero.projeto.specifications.JobSpecifications;
import com.studiozero.projeto.specifications.SubJobSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobRepository jobRepository;
    private final CommandRepository commandRepository;
    private final ProductRepository productRepository;
    private final SubJobRepository subJobRepository;

    public DashboardResponseDto allStats() {
        Double finReturnJob = jobRepository
                .findAll(JobSpecifications.hasStatus(Status.CLOSED))
                .stream()
                .mapToDouble(Job::getTotalValue)
                .sum();

        Double finReturnCommand = commandRepository
                .findAll(CommandSpecifications.hasStatus(Status.CLOSED))
                .stream()
                .mapToDouble(Command::getTotalValue)
                .sum();

        Double finOut = productRepository
                .findAll()
                .stream()
                .mapToDouble(p -> p.getQuantity() * p.getBuyValue())
                .sum();

        Double finReturnSubJob = subJobRepository
                .findAll()
                .stream()
                .filter(subJob -> subJob.getJob().getStatus() == Status.CLOSED)
                .mapToDouble(SubJob::getValue)
                .sum();

        YearMonth currentMonth = YearMonth.now();

        // Usando a data do SubJob (sub_servico) para determinar a frequência
        List<UUID> activeClients = subJobRepository
                .findAll()
                .stream()
                .filter(subJob -> {
                    LocalDate subJobDate = subJob.getDate();  // Aqui usamos a data do subserviço
                    LocalDate today = LocalDate.now();
                    LocalDate endOfMonth = currentMonth.atEndOfMonth();
                    return subJobDate != null && YearMonth.from(subJobDate).equals(currentMonth);
                })
                .map(subJob -> subJob.getJob().getClient().getId())
                .distinct()
                .collect(Collectors.toList());

        Double frequency = (double) activeClients.size();

        Double finReturn = finReturnJob + finReturnCommand + finReturnSubJob;

        return new DashboardResponseDto(frequency, finReturn, finOut);
    }

    public DashboardResponseDto clientStats(UUID id) {
        // Somar os valores dos jobs fechados
        Double finReturnJob = jobRepository
                .findAll(Specification.where(JobSpecifications.hasClient(id)).and(JobSpecifications.hasStatus(Status.CLOSED)))
                .stream()
                .mapToDouble(Job::getTotalValue)
                .sum();

        // Somar os valores das comandas fechadas
        Double finReturnCommand = commandRepository
                .findAll(Specification.where(CommandSpecifications.hasClient(id)).and(CommandSpecifications.hasStatus(Status.CLOSED)))
                .stream()
                .mapToDouble(Command::getTotalValue)
                .sum();

        // Somar os valores dos subjobs fechados
        Double finReturnSubJob = subJobRepository
                .findAll(SubJobSpecifications.hasClient(id))
                .stream()
                .filter(subJob -> subJob.getJob().getStatus() == Status.CLOSED)
                .mapToDouble(SubJob::getValue)
                .sum();

        // Total financeiro
        Double finReturn = finReturnJob + finReturnCommand + finReturnSubJob;

        YearMonth currentMonth = YearMonth.now();

        // Contagem de subjobs fechados no mês atual
        long frequency = subJobRepository
                .findAll(SubJobSpecifications.hasClient(id))
                .stream()
                .filter(subJob -> subJob.getJob().getStatus() == Status.CLOSED &&
                        YearMonth.from(subJob.getDate()).equals(currentMonth))
                .count();

        return new DashboardResponseDto((double) frequency, finReturn, null);
    }
}
