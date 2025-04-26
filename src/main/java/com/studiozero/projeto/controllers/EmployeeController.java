package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.EmployeeLoginRequestDTO;
import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeLoginResponseDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.entities.EmployeeUserDetails;
import com.studiozero.projeto.exceptions.UnauthorizedException;
import com.studiozero.projeto.mappers.EmployeeMapper;
import com.studiozero.projeto.services.EmployeeService;
import com.studiozero.projeto.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "Endpoints for Employee Management")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

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
    public ResponseEntity<EmployeeLoginResponseDTO> loginEmployee(
            @RequestBody @Valid EmployeeLoginRequestDTO employeeDto
    ) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                employeeDto.getEmail(),
                employeeDto.getPassword()
        );

        var auth = authenticationManager.authenticate(usernamePassword);

        var employeeUserDetails = (EmployeeUserDetails) auth.getPrincipal();

        var token = tokenService.generateToken(employeeUserDetails.getEmployee());

        return ResponseEntity.ok(new EmployeeLoginResponseDTO(token));
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

        return ResponseEntity.status(200).body(dtos);
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
