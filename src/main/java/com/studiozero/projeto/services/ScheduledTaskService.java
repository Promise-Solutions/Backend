package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.entities.SubJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final EmailService emailService;
    private final EmployeeService employeeService;
    private final SubJobService subJobService;

    // Cron: segundos minutos horas dia mês dia-da-semana
    // 4h30 da manhã todos os dias => "0 30 4 * * *"
    @Scheduled(cron = "0 0 4 * * *", zone = "America/Sao_Paulo")
    public void executar() {
        LocalDate hoje = LocalDate.now();
        log.info("🔔 Iniciando tarefa agendada - Verificando SubJobs para {}", hoje);

        List<SubJob> subJobsHoje = subJobService.listSubJobs().stream()
                .filter(sj -> sj.getDate() != null && sj.getDate().isEqual(hoje))
                .toList();

        if (subJobsHoje.isEmpty()) {
            log.info("✅ Nenhum SubJob agendado para hoje. Nenhum e-mail será enviado.");
            return;
        }

        log.info("📌 SubJobs encontrados para hoje: {}", subJobsHoje.size());

        List<Employee> employees = employeeService.listEmployees();
        List<String> emails = employees.stream()
                .map(Employee::getEmail)
                .filter(email -> email != null && !email.isBlank())
                .toList();

        if (emails.isEmpty()) {
            log.warn("⚠️ Nenhum e-mail válido encontrado entre os funcionários. Aviso não será enviado.");
            return;
        }

        String assunto = "StudioZero: Você tem atendimentos hoje!";
        String conteudo = "Olá! Você tem atendimentos presenciais agendados para hoje. Verifique sua agenda no sistema.";

        try {
            emailService.enviarAviso(emails, assunto, conteudo);
            log.info("📧 E-mails de aviso enviados com sucesso para: {}", emails);
        } catch (MailException e) {
            log.error("❌ Erro ao tentar enviar e-mails para os funcionários. Causa: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("❌ Erro inesperado ao executar tarefa agendada de envio de e-mail: {}", e.getMessage(), e);
        }
    }
}