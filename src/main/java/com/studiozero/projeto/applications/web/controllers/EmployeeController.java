package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.domain.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Endpoints for Employee Management")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
}
