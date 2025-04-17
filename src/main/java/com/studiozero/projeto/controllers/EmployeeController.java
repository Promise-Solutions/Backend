package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.EmployeeLoginRequestDTO;
import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.mappers.EmployeeMapper;
import com.studiozero.projeto.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "Endpoints for Employee Management")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(
            summary = "Create a employee",
            description = "This method is responsible for create a employee."
    )
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @RequestBody @Valid EmployeeRequestDTO employeeDto
    ) {
        Employee employee = EmployeeMapper.toEntity(employeeDto);
        employeeService.createEmployee(employee);

        return ResponseEntity.status(201).body(EmployeeMapper.toDTO(employee));
    }

    @Operation(
            summary = "Login a employee",
            description = "This method is responsible for login a employee."
    )
    @PostMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> loginEmployee(
            @RequestBody @Valid EmployeeLoginRequestDTO employeeDto
    ) {
        Employee employee = employeeService.login(employeeDto.getEmail(), employeeDto.getPassword());

        EmployeeResponseDTO employeeDtoLogin = EmployeeMapper.toDTO(employee);

        return ResponseEntity.ok(employeeDtoLogin);
    }

    @Operation(
            summary = "Search a employee",
            description = "This method is responsible for search a employee."
    )
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeById(
            @PathVariable @Valid UUID id
    ) {
        Employee employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(EmployeeMapper.toDTO(employee));
    }

    @Operation(
            summary = "List all employees",
            description = "This method is responsible for list all employees."
    )
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> listAllEmployees() {
        List<Employee> employees = employeeService.listEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<EmployeeResponseDTO> dtos = EmployeeMapper.toListDtos(employees);

        return ResponseEntity.status(302).body(dtos);
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
        Employee employee = EmployeeMapper.toEntity(employeeDto, id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        return ResponseEntity.ok(EmployeeMapper.toDTO(updatedEmployee));
    }

    @Operation(
            summary = "Delete a employee",
            description = "This method is responsible for delete a employee."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable @Valid UUID id
    ) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
