package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.domain.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Endpoints for Task Management")
public class TaskController {

    @Autowired
    private TaskService taskService;
}
