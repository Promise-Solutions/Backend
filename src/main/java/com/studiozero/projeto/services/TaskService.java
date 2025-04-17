package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.entities.Task;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.TaskMapper;
import com.studiozero.projeto.repositories.EmployeeRepository;
import com.studiozero.projeto.repositories.TaskRepository;
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
        Employee employee = employeeRepository.findById(taskdto.getFkEmployee())
                .orElse(null);

        Task task = taskMapper.toEntity(taskdto);
        task.setId(UUID.randomUUID());
        task.setEmployee(employee);
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
        }
        throw new NotFoundException("Task not found");
    }
}
