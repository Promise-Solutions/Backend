package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.EmployeeLoginRequestDTO;
import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.services.ClientService;
import com.studiozero.projeto.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
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

        @Operation(
                summary = "Create a employee",
                description = "This method is responsible for create a employee."
        )
        @PostMapping
        public ResponseEntity<EmployeeResponseDTO> createEmployee(
                @RequestBody @Valid EmployeeRequestDTO employeeDto
        ) {
                return ResponseEntity.status(201).body(employeeService.save(employeeDto));
        }

        @Operation(
                summary = "Login a employee",
                description = "This method is responsible for login a employee."
        )
        @PostMapping("/login")
        public ResponseEntity<EmployeeResponseDTO> loginEmployee(
                @RequestBody @Valid EmployeeLoginRequestDTO employeeDto
        ) {
                return ResponseEntity.ok(employeeService.login(employeeDto));
        }

        @Operation(
                summary = "Search a employee",
                description = "This method is responsible for search a employee."
        )
        @GetMapping("/{id}")
        public ResponseEntity<EmployeeResponseDTO> findEmployeeById(
                @PathVariable @Valid UUID id
        ) {
                return ResponseEntity.ok(employeeService.findById(id));
        }

        @Operation(
                summary = "List all employees",
                description = "This method is responsible for list all employees."
        )
        @GetMapping
        public ResponseEntity<List<EmployeeResponseDTO>> listAllEmployees() {
                return ResponseEntity.ok(employeeService.findAll());
        }

        @Operation(
                summary = "Update a employee",
                description = "This method is responsible for update a employee."
        )
        @PatchMapping("/{id}")
        public ResponseEntity<EmployeeResponseDTO> updateEmployee(
                @PathVariable @Valid UUID id,
                @RequestBody @Valid EmployeeRequestDTO employeeDto
        ) {
                return ResponseEntity.ok(employeeService.update(id, employeeDto));
        }

        @Operation(
                summary = "Delete a employee",
                description = "This method is responsible for delete a employee."
        )
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteEmployee(
                @PathVariable @Valid UUID id
        ) {
                employeeService.delete(id);
                return ResponseEntity.ok().build();
        }
}
