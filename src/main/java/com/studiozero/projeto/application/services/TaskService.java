package com.studiozero.projeto.application.services;

import com.studiozero.projeto.application.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.web.mappers.TaskMapper;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.domain.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskMapper taskMapper;

    public Task createTask(TaskRequestDTO taskdto) {
        Task task = taskMapper.toEntity(taskdto);

        if (taskdto.getFkEmployee() != null) {
            Employee employee = employeeRepository.findById(taskdto.getFkEmployee())
                    .orElse(null);
            task.setEmployee(employee);
        }

        task.setId(UUID.randomUUID());
        return taskRepository.save(task);
    }

    public Task findTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Task task) {
        if (taskRepository.existsById(task.getId())) {
            task.setId(task.getId());
            return taskRepository.save(task);
        }
        throw new NotFoundException("Task not found");
    }

    public void deleteTask(UUID id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new NotFoundException("Task not found");
        }
    }
}
