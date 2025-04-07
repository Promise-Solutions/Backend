package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.applications.web.dtos.request.EmployeeCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeSearchRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.domain.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Endpoints for Employee Management")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @RequestBody @Valid EmployeeCreateRequestDTO employeeDto
    ) {
        return ResponseEntity.ok(employeeService.save(employeeDto));
    }

    @GetMapping("/search")
    public ResponseEntity<EmployeeResponseDTO> searchEmployee(
            @PathVariable @Valid EmployeeSearchRequestDTO employeeDto
    ) {
        return ResponseEntity.ok(employeeService.findById(employeeDto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> listEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @PatchMapping
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @RequestBody @Valid EmployeeUpdateRequestDTO employeeDto
    ) {
        return ResponseEntity.ok(employeeService.update(employeeDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(
            @RequestBody @Valid EmployeeDeleteRequestDTO employeeDto
    ) {
        employeeService.delete(employeeDto);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
