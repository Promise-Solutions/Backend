package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.entities.Task;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.TaskMapper;
import com.studiozero.projeto.repositories.ProductRepository;
import com.studiozero.projeto.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ProductRepository productRepository;


    public TaskResponseDTO save(TaskRequestDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task savedTask = taskRepository.save(task);

        return taskMapper.toDTO(savedTask);
    }

    public TaskResponseDTO findById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return taskMapper.toDTO(task);

    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    public TaskResponseDTO update(UUID id, TaskRequestDTO taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStartDate(taskDto.getStartDate());
        task.setLimitDate(taskDto.getLimitDate());
        task.setStatus(taskDto.getStatus());
        task.setFkEmployee(taskDto.getFkEmployee());

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toDTO(updatedTask);
    }

    public void delete (UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
