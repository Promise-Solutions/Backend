package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.EmployeeService;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.exceptions.ConflictException;
import com.studiozero.projeto.infrastructure.exceptions.DeleteOwnUserException;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should throw ConflictException when CPF already exists")
    void shouldThrowConflictExceptionWhenCpfAlreadyExists() {
        Employee employee = new Employee();
        employee.setCpf("12345678900");
        employee.setEmail("test@example.com");

        when(employeeRepository.existsByCpf(employee.getCpf())).thenReturn(true);

        assertThrows(ConflictException.class, () -> employeeService.createEmployee(employee));
        verify(employeeRepository).existsByCpf(employee.getCpf());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw ConflictException when email already exists")
    void shouldThrowConflictExceptionWhenEmailAlreadyExists() {
        Employee employee = new Employee();
        employee.setCpf("12345678900");
        employee.setEmail("test@example.com");

        when(employeeRepository.existsByCpf(employee.getCpf())).thenReturn(false);
        when(employeeRepository.existsByEmail(employee.getEmail())).thenReturn(true);

        assertThrows(ConflictException.class, () -> employeeService.createEmployee(employee));
        verify(employeeRepository).existsByCpf(employee.getCpf());
        verify(employeeRepository).existsByEmail(employee.getEmail());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should create employee when CPF and email are unique")
    void shouldCreateEmployeeWhenCpfAndEmailAreUnique() {
        Employee employee = new Employee();
        employee.setCpf("12345678900");
        employee.setEmail("test@example.com");
        employee.setPassword("plainPassword");

        when(employeeRepository.existsByCpf(employee.getCpf())).thenReturn(false);
        when(employeeRepository.existsByEmail(employee.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(employee.getPassword())).thenReturn("encodedPassword");
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee created = employeeService.createEmployee(employee);

        assertNotNull(created.getId());
        assertEquals("encodedPassword", created.getPassword());
        verify(employeeRepository).save(employee);
    }

    @Test
    @DisplayName("Should return employee when ID exists")
    void shouldReturnEmployeeWhenIdExists() {
        UUID id = UUID.randomUUID();
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Fulano");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee found = employeeService.findEmployeeById(id);

        assertEquals(id, found.getId());
        assertEquals("Fulano", found.getName());
        verify(employeeRepository).findById(id);
    }

    @Test
    @DisplayName("Should throw NotFoundException when employee does not exist")
    void shouldThrowNotFoundExceptionWhenEmployeeDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> employeeService.findEmployeeById(id));
        verify(employeeRepository).findById(id);
    }

    @Test
    @DisplayName("Should return list of employees")
    void shouldReturnListOfEmployees() {
        List<Employee> employees = List.of(
                new Employee(UUID.randomUUID(), "João", "joao@example.com", "12345678900", "123456789", "senha123", true),
                new Employee(UUID.randomUUID(), "Maria", "maria@example.com", "09876543211", "987654321", "senha456", true)
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.listEmployees();

        assertEquals(2, result.size());
        assertEquals("João", result.get(0).getName());
        assertEquals("Maria", result.get(1).getName());
        verify(employeeRepository).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no employees exist")
    void shouldReturnEmptyListWhenNoEmployeesExist() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        List<Employee> result = employeeService.listEmployees();

        assertTrue(result.isEmpty());
        verify(employeeRepository).findAll();
    }

    @Test
    @DisplayName("Should update all fields when all are non-null")
    void shouldUpdateAllFieldsWhenAllAreNonNull() {
        UUID id = UUID.randomUUID();

        Employee existing = new Employee();
        existing.setId(id);
        existing.setName("Old Name");
        existing.setEmail("old@example.com");
        existing.setContact("12345");
        existing.setCpf("11111111111");
        existing.setPassword("oldPassword");
        existing.setActive(true);

        Employee update = new Employee();
        update.setId(id);
        update.setName("New Name");
        update.setEmail("new@example.com");
        update.setContact("67890");
        update.setCpf("22222222222");
        update.setPassword("newPassword");
        update.setActive(false);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existing));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.updateEmployee(update);

        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        assertEquals("67890", result.getContact());
        assertEquals("22222222222", result.getCpf());
        assertEquals("encodedNewPassword", result.getPassword());
        assertFalse(result.getActive());

        verify(employeeRepository).findById(id);
        verify(employeeRepository).save(existing);
    }

    @Test
    @DisplayName("Should update only non-null fields")
    void shouldUpdateOnlyNonNullFields() {
        UUID id = UUID.randomUUID();

        Employee existing = new Employee();
        existing.setId(id);
        existing.setName("Old Name");
        existing.setEmail("old@example.com");
        existing.setContact("12345");
        existing.setCpf("11111111111");
        existing.setPassword("oldPassword");
        existing.setActive(true);

        Employee update = new Employee();
        update.setId(id);
        update.setName(null);
        update.setEmail("new@example.com");
        update.setContact(null);
        update.setCpf("22222222222");
        update.setPassword(null);
        update.setActive(null);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.updateEmployee(update);

        assertEquals("Old Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        assertEquals("12345", result.getContact());
        assertEquals("22222222222", result.getCpf());
        assertEquals("oldPassword", result.getPassword());
        assertTrue(result.getActive());

        verify(employeeRepository).findById(id);
        verify(employeeRepository).save(existing);
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existent employee")
    void shouldThrowNotFoundExceptionWhenEmployeeNotFound() {
        UUID id = UUID.randomUUID();
        Employee update = new Employee();
        update.setId(id);

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> employeeService.updateEmployee(update));

        verify(employeeRepository).findById(id);
        verify(employeeRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    @DisplayName("Should delete employee when exists")
    void shouldDeleteEmployeeWhenExists() {
        UUID id = UUID.randomUUID();
        UUID userLogged = UUID.randomUUID();

        Employee employee = new Employee();
        employee.setId(id);

        when(employeeRepository.existsById(id)).thenReturn(true);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(id, userLogged);

        verify(employeeRepository).existsById(id);
        verify(employeeRepository).findById(id);
        verify(employeeRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existent employee")
    void shouldThrowNotFoundExceptionWhenDeletingNonExistentEmployee() {
        UUID id = UUID.randomUUID();
        UUID userLogged = UUID.randomUUID();

        when(employeeRepository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> employeeService.deleteEmployee(id, userLogged));

        verify(employeeRepository).existsById(id);
        verify(employeeRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("deleteEmployee throws NotFoundException if employee does not exist")
    void deleteEmployee_shouldThrowNotFoundException_whenEmployeeNotFound() {
        UUID employeeId = UUID.randomUUID();
        UUID userLogged = UUID.randomUUID();

        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            employeeService.deleteEmployee(employeeId, userLogged);
        });

        assertEquals("Employee not found", exception.getMessage());
        verify(employeeRepository, never()).findById(any());
        verify(employeeRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("deleteEmployee throws DeleteOwnUserException if user tries to delete themselves")
    void deleteEmployee_shouldThrowDeleteOwnUserException_whenDeletingOwnUser() {
        UUID employeeId = UUID.randomUUID();
        UUID userLogged = employeeId;

        Employee employee = new Employee();
        employee.setId(employeeId);

        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        DeleteOwnUserException exception = assertThrows(DeleteOwnUserException.class, () -> {
            employeeService.deleteEmployee(employeeId, userLogged);
        });

        assertEquals("You can't delete your own user", exception.getMessage());
        verify(employeeRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Should throw NotFoundException when employee not found after existsById returns true")
    void shouldThrowNotFoundExceptionIfFindByIdReturnsEmpty() {
        UUID employeeId = UUID.randomUUID();
        UUID userLogged = UUID.randomUUID();

        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.deleteEmployee(employeeId, userLogged))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Employee not found");

        verify(employeeRepository, never()).deleteById(any());
    }
}
