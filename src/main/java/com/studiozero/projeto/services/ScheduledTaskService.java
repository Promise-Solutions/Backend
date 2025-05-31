package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final EmailService emailService;
    private final EmployeeService employeeService;

    // Cron: segundos minutos horas dia mes dia-da-semana
    // 5h da manhã todos os dias => "0 0 4 * * *"
    @Scheduled(cron = "0 0 4 * * *", zone = "America/Sao_Paulo")
    public void executar() {
        List<Employee> employees = employeeService.listEmployees();
        List<String> emails = employees.stream()
                .map(Employee::getEmail)
                .filter(email -> email != null && !email.isBlank())
                .collect(Collectors.toList());

        String assunto = "StudioZero: Você tem atendimentos hoje!";
        String conteudo = """
        Olá, confira os atendimentos presenciais que terá hoje!.
        """;

        emailService.enviarAviso(emails, assunto, conteudo);
    }
}
