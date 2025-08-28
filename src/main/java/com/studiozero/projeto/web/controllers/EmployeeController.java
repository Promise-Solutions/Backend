package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.employee.*;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.entities.EmployeeUserDetails;
import com.studiozero.projeto.infrastructure.services.EncryptPasswordService;
import com.studiozero.projeto.web.dtos.request.EmployeeLoginRequestDTO;
import com.studiozero.projeto.web.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.web.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.EmployeeLoginResponseDTO;
import com.studiozero.projeto.web.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.web.mappers.EmployeeMapper;
import com.studiozero.projeto.infrastructure.services.GenerateTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Endpoints for Employee Management")
public class EmployeeController {

        private final CreateEmployeeUseCase createEmployeeUseCase;
        private final GetEmployeeUseCase getEmployeeUseCase;
        private final UpdateEmployeeUseCase updateEmployeeUseCase;
        private final LoginEmployeeUseCase loginEmployeeUseCase;
        private final DeleteEmployeeWithUserUseCase deleteEmployeeWithUserUseCase;
        private final ListEmployeesUseCase listEmployeesUseCase;
        private final AuthenticationManager authenticationManager;
        private final GenerateTokenService generateTokenService;
        private final EncryptPasswordService encryptPasswordService;

    public EmployeeController(CreateEmployeeUseCase createEmployeeUseCase,
                              GetEmployeeUseCase getEmployeeUseCase,
                              UpdateEmployeeUseCase updateEmployeeUseCase,
                              LoginEmployeeUseCase loginEmployeeUseCase,
                              DeleteEmployeeWithUserUseCase deleteEmployeeWithUserUseCase,
                              ListEmployeesUseCase listEmployeesUseCase,
                              AuthenticationManager authenticationManager,
                              GenerateTokenService generateTokenService,
                              EncryptPasswordService encryptPasswordService) {
        this.encryptPasswordService = encryptPasswordService;
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.getEmployeeUseCase = getEmployeeUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.loginEmployeeUseCase = loginEmployeeUseCase;
        this.deleteEmployeeWithUserUseCase = deleteEmployeeWithUserUseCase;
        this.listEmployeesUseCase = listEmployeesUseCase;
        this.authenticationManager = authenticationManager;
        this.generateTokenService = generateTokenService;
    }

    @Operation(summary = "Create a employee", description = "This method is responsible for create a employee.")
        @PostMapping
        public ResponseEntity<EmployeeResponseDTO> createEmployee(
                        @RequestBody @Valid EmployeeRequestDTO employeeDto) {

                Employee employee = EmployeeMapper.toDomain(employeeDto);
                Employee employeeToEncrypt = encryptPasswordService.encryptPassword(employee);
                createEmployeeUseCase.execute(employeeToEncrypt);
                return ResponseEntity.status(201).body(EmployeeMapper.toDTO(employee));
        }

        @Operation(summary = "Login a employee", description = "This method is responsible for login a employee.")
        @PostMapping("/login")
        public ResponseEntity<EmployeeLoginResponseDTO> loginEmployee(
                        @RequestBody @Valid EmployeeLoginRequestDTO employeeDto) {
                var usernamePassword = new UsernamePasswordAuthenticationToken(
                                employeeDto.getEmail(),
                                employeeDto.getPassword());

                var auth = authenticationManager.authenticate(usernamePassword);

                var employeeUserDetails = (EmployeeUserDetails) auth.getPrincipal();

                var token = generateTokenService.execute(employeeUserDetails.getEmployee());
                var id = employeeUserDetails.getEmployee().getId();

                return ResponseEntity.ok(new EmployeeLoginResponseDTO(token, id));
        }

        @Operation(summary = "Search a employee", description = "This method is responsible for search a employee.")
        @GetMapping("/{id}")
        public ResponseEntity<EmployeeResponseDTO> findEmployeeById(
                        @PathVariable @Valid UUID id) {
                Employee employee = getEmployeeUseCase.execute(id);
                return ResponseEntity.ok(EmployeeMapper.toDTO(employee));
        }

        @Operation(summary = "List all employees", description = "This method is responsible for list all employees.")
        @GetMapping
        public ResponseEntity<List<EmployeeResponseDTO>> listAllEmployees() {
                List<Employee> employees = listEmployeesUseCase.execute();
                if (employees.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                List<EmployeeResponseDTO> dtos = EmployeeMapper.toDTOList(employees);
                return ResponseEntity.status(200).body(dtos);
        }

        @Operation(summary = "Update a employee", description = "This method is responsible for update a employee.")
        @PatchMapping("/{id}")
        public ResponseEntity<EmployeeResponseDTO> updateEmployee(
                        @PathVariable @Valid UUID id,
                        @RequestBody @Valid EmployeeUpdateRequestDTO employeeDto) {
                Employee employee = EmployeeMapper.toDomain(employeeDto, id);
                Employee updatedEmployee = updateEmployeeUseCase.execute(employee);
                return ResponseEntity.ok(EmployeeMapper.toDTO(updatedEmployee));
        }

        @Operation(summary = "Delete a employee", description = "This method is responsible for delete a employee.")
        @DeleteMapping("/{id}/{userLogged}")
        public ResponseEntity<Void> deleteEmployee(
                        @PathVariable @Valid UUID id,
                        @PathVariable @Valid UUID userLogged) {
                deleteEmployeeWithUserUseCase.execute(id, userLogged);
                return ResponseEntity.ok().build();
        }
}
