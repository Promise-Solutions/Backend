package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.TaskRepository;
import com.studiozero.projeto.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
}
