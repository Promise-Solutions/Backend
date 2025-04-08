package com.studiozero.projeto.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@Tag(name = "Jobs", description = "Endpoints for Job Management")
public class JobController {

    @Autowired
    private JobService jobService;
}
