package com.studiozero.projeto.infrastructure.broker.producer;

import com.studiozero.projeto.application.usecases.employee.ListEmployeesUseCase;
import com.studiozero.projeto.application.usecases.subjob.ListTodaySubJobsUseCase;
import com.studiozero.projeto.application.usecases.task.ListTodayTasksUseCase;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.infrastructure.broker.producer.dto.EventDto;
import com.studiozero.projeto.infrastructure.broker.producer.dto.SubJobEmailEventDto;
import com.studiozero.projeto.infrastructure.broker.producer.dto.TaskEmailEventDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyEmailProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ListTodaySubJobsUseCase listTodaySubJobsUseCase;
    private final ListTodayTasksUseCase listTodayTasksUseCase;
    private final ListEmployeesUseCase listEmployeesUseCase;

    public DailyEmailProducer(RabbitTemplate rabbitTemplate, ListTodaySubJobsUseCase listTodaySubJobsUseCase, ListTodayTasksUseCase listTodayTasksUseCase, ListEmployeesUseCase listEmployeesUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.listTodaySubJobsUseCase = listTodaySubJobsUseCase;
        this.listTodayTasksUseCase = listTodayTasksUseCase;
        this.listEmployeesUseCase = listEmployeesUseCase;
    }

    public void sendEvent() {
        List<SubJob> subJobs = listTodaySubJobsUseCase.execute();
        List<Task> tasks = listTodayTasksUseCase.execute();
        List<Employee> employees = listEmployeesUseCase.execute();

        List<SubJobEmailEventDto> subJobEmailEvent = subJobs.stream()
                .map(sj -> new SubJobEmailEventDto(sj.getJob().getClient().getName(), sj.getTitle()))
                .toList();

        List<TaskEmailEventDto> taskEmailEvent = tasks.stream()
                .map(t -> new TaskEmailEventDto(t.getTitle(), t.getLimitDate()))
                .toList();

        List<String> employeesEmail = employees.stream()
                .map(Employee::getEmail)
                .toList();

        rabbitTemplate.convertAndSend(new EventDto(employeesEmail, subJobEmailEvent, taskEmailEvent));
    }
}
