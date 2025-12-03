package com.studiozero.projeto.infrastructure.services;

import com.studiozero.projeto.domain.repositories.EmailRepository;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.application.usecases.subjob.ListSubJobsUseCase;
import com.studiozero.projeto.application.usecases.employee.ListEmployeesUseCase;
import com.studiozero.projeto.application.usecases.task.ListTasksUseCase;
import com.studiozero.projeto.domain.entities.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduledEmailTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduledEmailTask.class);

    private final ListSubJobsUseCase listSubJobsUseCase;
    private final ListEmployeesUseCase listEmployeesUseCase;
    private final ListTasksUseCase listTasksUseCase;
    private final EmailRepository emailRepository;

    public ScheduledEmailTask(ListSubJobsUseCase listSubJobsUseCase, ListEmployeesUseCase listEmployeesUseCase, ListTasksUseCase listTasksUseCase, EmailRepository emailRepository) {
        this.listSubJobsUseCase = listSubJobsUseCase;
        this.listEmployeesUseCase = listEmployeesUseCase;
        this.listTasksUseCase = listTasksUseCase;
        this.emailRepository = emailRepository;
    }

    // 4am every day
    @Scheduled(cron = "0 0 4 * * *", zone = "America/Sao_Paulo")
    public void execute() {
        LocalDate today = LocalDate.now();
        log.info("🔔 Starting scheduled task - Checking SubJobs and Tasks for {}", today);

        List<SubJob> subJobsToday = listSubJobsUseCase.execute().stream()
                .filter(sj -> sj.getDate() != null && sj.getDate().isEqual(today))
                .toList();

        List<Task> tasksToday = listTasksUseCase.execute().stream()
                .filter(task -> task.getLimitDate() != null && task.getLimitDate().isEqual(today))
                .toList();

        if (subJobsToday.isEmpty() && tasksToday.isEmpty()) {
            log.info("✅ No SubJob or Task scheduled for today. No email will be sent.");
            return;
        }

        log.info("📌 SubJobs found for today: {}", subJobsToday.size());
        log.info("📌 Tasks found for today: {}", tasksToday.size());

        List<Employee> employees = listEmployeesUseCase.execute();
        List<String> emails = employees.stream()
                .map(Employee::getEmail)
                .filter(email -> email != null && !email.isBlank())
                .toList();

        if (emails.isEmpty()) {
            log.warn("⚠️ No valid email found among employees. Notification will not be sent.");
            return;
        }

        StringBuilder content = new StringBuilder();
        content.append("Olá, aqui está suas tarefas e atendimentos do dia!.\n\n");
        if (!subJobsToday.isEmpty()) {
            content.append("Atendimentos para hoje:\n");
            for (SubJob sj : subJobsToday) {
                String clientName = sj.getJob() != null && sj.getJob().getClient() != null ? sj.getJob().getClient().getName() : "Não informado";
                content.append("- ").append(sj.getTitle()).append(" (Cliente: ").append(clientName).append(")\n");
            }
            content.append("\n");
        }
        if (!tasksToday.isEmpty()) {
            content.append("Tarefas para hoje:\n");
            for (Task t : tasksToday) {
                content.append("- ").append(t.getTitle()).append(" (Prazo: ").append(t.getLimitDate()).append(")\n");
            }
        }

        String subject = "StudioZero: Você tem deveres para hoje!";

        try {
            emailRepository.sendEmail(emails, subject, content.toString());
            log.info("📧 Notification emails sent successfully to: {}", emails);
        } catch (MailException e) {
            log.error("❌ Error trying to send emails to employees. Cause: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("❌ Unexpected error while executing scheduled email task: {}", e.getMessage(), e);
        }
    }
}
