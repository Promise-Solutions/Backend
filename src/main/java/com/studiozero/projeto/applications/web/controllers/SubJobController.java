package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.domain.services.SubJobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub-jobs")
@Tag(name = "Sub Jobs", description = "Endpoints for Sub Job Management")
public class SubJobController {

    @Autowired
    private SubJobService subJobService;
}
