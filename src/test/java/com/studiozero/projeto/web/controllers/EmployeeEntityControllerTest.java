package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.employee.*;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.services.EncryptPasswordService;
import com.studiozero.projeto.infrastructure.services.GenerateTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateEmployeeUseCase create = new CreateEmployeeUseCase(null) {
            @Override
            public Employee execute(Employee employee) { return employee; }
        };

        GetEmployeeUseCase get = new GetEmployeeUseCase(null) {
            @Override
            public Employee execute(UUID id) { throw new IllegalArgumentException("not found"); }
        };

        UpdateEmployeeUseCase update = new UpdateEmployeeUseCase(null) {
            @Override
            public Employee execute(Employee employee) { return employee; }
        };

        LoginEmployeeUseCase login = new LoginEmployeeUseCase(null) {
            @Override
            public Employee execute(String email, String password) { return null; }
        };

        DeleteEmployeeWithUserUseCase delete = new DeleteEmployeeWithUserUseCase(null) {
            @Override
            public void execute(UUID id, UUID userLogged) { }
        };

        ListEmployeesUseCase list = new ListEmployeesUseCase(null) {
            @Override
            public java.util.List<Employee> execute() { return List.of(); }
        };

        AuthenticationManager authManager = authentication -> authentication;
        GenerateTokenService tokenService = new GenerateTokenService("secret", 3600L) {
            @Override
            public String execute(Employee employee) { return "token"; }
        };
        EncryptPasswordService encrypt = new EncryptPasswordService() {
            @Override
            public Employee encryptPassword(Employee employee) { return employee; }
        };

        EmployeeController controller = new EmployeeController(create, get, update, login, delete, list, authManager, tokenService, encrypt);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isNoContent());
    }
}
