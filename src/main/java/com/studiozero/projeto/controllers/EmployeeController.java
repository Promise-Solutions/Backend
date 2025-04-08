package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.services.ClientService;
import com.studiozero.projeto.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Endpoints for Employee Management")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @RequestBody @Valid EmployeeRequestDTO employeeDto
    ) {
        return ResponseEntity.ok(employeeService.save(employeeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeById(
            @PathVariable @Valid UUID id
            ) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> listAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PatchMapping("/{ID}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable UUID id,
            @RequestBody @Valid EmployeeRequestDTO employeeDto
    ) {
        return ResponseEntity.ok(employeeService.update(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable @Valid UUID id
    ) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
}
