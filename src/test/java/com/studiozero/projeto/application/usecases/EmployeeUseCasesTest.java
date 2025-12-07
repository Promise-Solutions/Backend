package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.employee.*;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.web.handlers.DeleteOwnUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeUseCasesTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Nested
    class CreateEmployeeUseCaseTests {
        private CreateEmployeeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateEmployeeUseCase(employeeRepository);
        }

        @Test
        void shouldCreateEmployeeSuccessfully() {
            Employee employee = new Employee();
            employee.setCpf("12345678900");
            employee.setEmail("test@example.com");

            when(employeeRepository.existsByCpf("12345678900")).thenReturn(false);
            when(employeeRepository.existsByEmail("test@example.com")).thenReturn(false);

            Employee result = useCase.execute(employee);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertEquals("12345678900", result.getCpf());
            assertEquals("test@example.com", result.getEmail());
            verify(employeeRepository, times(1)).existsByCpf("12345678900");
            verify(employeeRepository, times(1)).existsByEmail("test@example.com");
            verify(employeeRepository, times(1)).save(employee);
        }

        @Test
        void shouldCreateEmployeeWithExistingId() {
            UUID existingId = UUID.randomUUID();
            Employee employee = new Employee();
            employee.setId(existingId);
            employee.setCpf("12345678900");
            employee.setEmail("test@example.com");

            when(employeeRepository.existsByCpf("12345678900")).thenReturn(false);
            when(employeeRepository.existsByEmail("test@example.com")).thenReturn(false);

            Employee result = useCase.execute(employee);

            assertNotNull(result);
            assertEquals(existingId, result.getId());
            verify(employeeRepository, times(1)).save(employee);
        }

        @Test
        void shouldThrowExceptionWhenEmployeeIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Funcionário inválido", exception.getMessage());
            verify(employeeRepository, never()).existsByCpf(anyString());
            verify(employeeRepository, never()).existsByEmail(anyString());
            verify(employeeRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenCpfAlreadyExists() {
            Employee employee = new Employee();
            employee.setCpf("12345678900");
            employee.setEmail("test@example.com");

            when(employeeRepository.existsByCpf("12345678900")).thenReturn(true);

            IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> useCase.execute(employee));

            assertEquals("Funcionário já existe com este CPF", exception.getMessage());
            verify(employeeRepository, times(1)).existsByCpf("12345678900");
            verify(employeeRepository, never()).existsByEmail(anyString());
            verify(employeeRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenEmailAlreadyExists() {
            Employee employee = new Employee();
            employee.setCpf("12345678900");
            employee.setEmail("test@example.com");

            when(employeeRepository.existsByCpf("12345678900")).thenReturn(false);
            when(employeeRepository.existsByEmail("test@example.com")).thenReturn(true);

            IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> useCase.execute(employee));

            assertEquals("Funcionário já existe com este e-mail ", exception.getMessage());
            verify(employeeRepository, times(1)).existsByCpf("12345678900");
            verify(employeeRepository, times(1)).existsByEmail("test@example.com");
            verify(employeeRepository, never()).save(any());
        }

        @Test
        void shouldGenerateIdWhenEmployeeHasNoId() {
            Employee employee = new Employee();
            employee.setCpf("12345678900");
            employee.setEmail("test@example.com");
            assertNull(employee.getId());

            when(employeeRepository.existsByCpf("12345678900")).thenReturn(false);
            when(employeeRepository.existsByEmail("test@example.com")).thenReturn(false);

            Employee result = useCase.execute(employee);

            assertNotNull(result.getId());
            verify(employeeRepository, times(1)).save(employee);
        }

        @Test
        void shouldValidateCpfBeforeEmail() {
            Employee employee = new Employee();
            employee.setCpf("12345678900");
            employee.setEmail("test@example.com");

            when(employeeRepository.existsByCpf("12345678900")).thenReturn(true);

            assertThrows(IllegalStateException.class, () -> useCase.execute(employee));

            verify(employeeRepository, times(1)).existsByCpf("12345678900");
            verify(employeeRepository, never()).existsByEmail(anyString());
        }
    }

    @Nested
    class DeleteEmployeeUseCaseTests {
        private DeleteEmployeeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteEmployeeUseCase(employeeRepository);
        }

        @Test
        void shouldDeleteEmployeeSuccessfully() {
            UUID employeeId = UUID.randomUUID();
            Employee employee = new Employee();
            employee.setId(employeeId);

            when(employeeRepository.findById(employeeId)).thenReturn(employee);

            useCase.execute(employeeId);

            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, times(1)).deleteById(employeeId);
        }

        @Test
        void shouldThrowExceptionWhenEmployeeNotFound() {
            UUID employeeId = UUID.randomUUID();

            when(employeeRepository.findById(employeeId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(employeeId));

            assertEquals("Funcionário não encontrado", exception.getMessage());
            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, never()).deleteById(any());
        }

        @Test
        void shouldCheckEmployeeExistenceBeforeDeleting() {
            UUID employeeId = UUID.randomUUID();

            when(employeeRepository.findById(employeeId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(employeeId));

            verify(employeeRepository, never()).deleteById(any());
        }
    }

    @Nested
    class DeleteEmployeeWithUserUseCaseTests {
        private DeleteEmployeeWithUserUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteEmployeeWithUserUseCase(employeeRepository);
        }

        @Test
        void shouldDeleteEmployeeWhenDifferentFromLoggedUser() {
            UUID employeeId = UUID.randomUUID();
            UUID loggedUserId = UUID.randomUUID();

            useCase.execute(employeeId, loggedUserId);

            verify(employeeRepository, times(1)).deleteById(employeeId);
        }

        @Test
        void shouldThrowExceptionWhenTryingToDeleteOwnUser() {
            UUID userId = UUID.randomUUID();

            DeleteOwnUserException exception = assertThrows(DeleteOwnUserException.class,
                    () -> useCase.execute(userId, userId));

            assertEquals("Você não pode excluir a si mesmo.", exception.getMessage());
            verify(employeeRepository, never()).deleteById(any());
        }

        @Test
        void shouldValidateUserBeforeDeleting() {
            UUID userId = UUID.randomUUID();

            assertThrows(DeleteOwnUserException.class,
                    () -> useCase.execute(userId, userId));

            verify(employeeRepository, never()).deleteById(any());
        }
    }

    @Nested
    class GetEmployeeUseCaseTests {
        private GetEmployeeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetEmployeeUseCase(employeeRepository);
        }

        @Test
        void shouldReturnEmployeeWhenFound() {
            UUID employeeId = UUID.randomUUID();
            Employee expectedEmployee = new Employee();
            expectedEmployee.setId(employeeId);
            expectedEmployee.setName("John Doe");
            expectedEmployee.setEmail("john@example.com");

            when(employeeRepository.findById(employeeId)).thenReturn(expectedEmployee);

            Employee result = useCase.execute(employeeId);

            assertNotNull(result);
            assertEquals(employeeId, result.getId());
            assertEquals("John Doe", result.getName());
            assertEquals("john@example.com", result.getEmail());
            verify(employeeRepository, times(1)).findById(employeeId);
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            NullPointerException exception = assertThrows(NullPointerException.class,
                    () -> useCase.execute(null));

            assertEquals("id must not be null", exception.getMessage());
            verify(employeeRepository, never()).findById(any());
        }

        @Test
        void shouldThrowExceptionWhenEmployeeNotFound() {
            UUID employeeId = UUID.randomUUID();

            when(employeeRepository.findById(employeeId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(employeeId));

            assertEquals("employee not found for id: " + employeeId, exception.getMessage());
            verify(employeeRepository, times(1)).findById(employeeId);
        }

        @Test
        void shouldReturnSameEmployeeInstanceFromRepository() {
            UUID employeeId = UUID.randomUUID();
            Employee expectedEmployee = new Employee();
            expectedEmployee.setId(employeeId);

            when(employeeRepository.findById(employeeId)).thenReturn(expectedEmployee);

            Employee result = useCase.execute(employeeId);

            assertSame(expectedEmployee, result);
        }

        @Test
        void shouldValidateIdBeforeCallingRepository() {
            assertThrows(NullPointerException.class, () -> useCase.execute(null));

            verify(employeeRepository, never()).findById(any());
        }
    }

    @Nested
    class ListEmployeesUseCaseTests {
        private ListEmployeesUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListEmployeesUseCase(employeeRepository);
        }

        @Test
        void shouldReturnListOfEmployees() {
            Employee employee1 = new Employee();
            employee1.setId(UUID.randomUUID());
            employee1.setName("John Doe");

            Employee employee2 = new Employee();
            employee2.setId(UUID.randomUUID());
            employee2.setName("Jane Smith");

            List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);

            when(employeeRepository.findAll()).thenReturn(expectedEmployees);

            List<Employee> result = useCase.execute();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("John Doe", result.get(0).getName());
            assertEquals("Jane Smith", result.get(1).getName());
            verify(employeeRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnEmptyListWhenNoEmployeesExist() {
            when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

            List<Employee> result = useCase.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            assertEquals(0, result.size());
            verify(employeeRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnSameListInstanceFromRepository() {
            List<Employee> expectedList = Arrays.asList(new Employee(), new Employee());

            when(employeeRepository.findAll()).thenReturn(expectedList);

            List<Employee> result = useCase.execute();

            assertSame(expectedList, result);
            verify(employeeRepository, times(1)).findAll();
        }

        @Test
        void shouldCallRepositoryFindAllExactlyOnce() {
            when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

            useCase.execute();

            verify(employeeRepository, times(1)).findAll();
            verifyNoMoreInteractions(employeeRepository);
        }
    }

    @Nested
    class LoginEmployeeUseCaseTests {
        private LoginEmployeeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new LoginEmployeeUseCase(employeeRepository);
        }

        @Test
        void shouldLoginEmployeeSuccessfully() {
            String email = "john@example.com";
            String password = "password123";
            Employee expectedEmployee = new Employee();
            expectedEmployee.setEmail(email);
            expectedEmployee.setName("John Doe");

            when(employeeRepository.findByEmailAndPassword(email, password)).thenReturn(expectedEmployee);

            Employee result = useCase.execute(email, password);

            assertNotNull(result);
            assertEquals(email, result.getEmail());
            assertEquals("John Doe", result.getName());
            verify(employeeRepository, times(1)).findByEmailAndPassword(email, password);
        }

        @Test
        void shouldThrowExceptionWhenCredentialsAreInvalid() {
            String email = "john@example.com";
            String password = "wrongpassword";

            when(employeeRepository.findByEmailAndPassword(email, password)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(email, password));

            assertEquals("Funcionário não encontrado", exception.getMessage());
            verify(employeeRepository, times(1)).findByEmailAndPassword(email, password);
        }

        @Test
        void shouldReturnSameEmployeeInstanceFromRepository() {
            String email = "john@example.com";
            String password = "password123";
            Employee expectedEmployee = new Employee();

            when(employeeRepository.findByEmailAndPassword(email, password)).thenReturn(expectedEmployee);

            Employee result = useCase.execute(email, password);

            assertSame(expectedEmployee, result);
        }

        @Test
        void shouldHandleNullEmail() {
            String password = "password123";

            when(employeeRepository.findByEmailAndPassword(null, password)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null, password));

            assertEquals("Funcionário não encontrado", exception.getMessage());
        }

        @Test
        void shouldHandleNullPassword() {
            String email = "john@example.com";

            when(employeeRepository.findByEmailAndPassword(email, null)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(email, null));

            assertEquals("Funcionário não encontrado", exception.getMessage());
        }
    }

    @Nested
    class UpdateEmployeeUseCaseTests {
        private UpdateEmployeeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateEmployeeUseCase(employeeRepository);
        }

        @Test
        void shouldUpdateEmployeeSuccessfully() {
            UUID employeeId = UUID.randomUUID();

            Employee existing = new Employee();
            existing.setId(employeeId);
            existing.setName("Old Name");
            existing.setEmail("old@example.com");
            existing.setContact("111111111");
            existing.setCpf("111.111.111-11");
            existing.setActive(true);

            Employee updated = new Employee();
            updated.setId(employeeId);
            updated.setName("New Name");
            updated.setEmail("new@example.com");
            updated.setContact("999999999");
            updated.setCpf("999.999.999-99");
            updated.setActive(false);

            when(employeeRepository.findById(employeeId)).thenReturn(existing);

            Employee result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(employeeId, result.getId());
            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, times(1)).save(existing);
        }

        @Test
        void shouldThrowExceptionWhenEmployeeIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Funcionário inválido", exception.getMessage());
            verify(employeeRepository, never()).findById(any());
            verify(employeeRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            Employee updated = new Employee();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Funcionário inválido", exception.getMessage());
            verify(employeeRepository, never()).findById(any());
            verify(employeeRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenEmployeeNotFound() {
            UUID employeeId = UUID.randomUUID();
            Employee updated = new Employee();
            updated.setId(employeeId);

            when(employeeRepository.findById(employeeId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Funcionário não encontrado", exception.getMessage());
            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, never()).save(any());
        }

        @Test
        void shouldUpdateAllEmployeeFields() {
            UUID employeeId = UUID.randomUUID();

            Employee existing = new Employee();
            existing.setId(employeeId);
            existing.setName("Old Name");
            existing.setEmail("old@example.com");
            existing.setContact("111111111");
            existing.setCpf("11111111111");
            existing.setActive(true);

            Employee updated = new Employee();
            updated.setId(employeeId);
            updated.setName("New Name");
            updated.setEmail("new@example.com");
            updated.setContact("999999999");
            updated.setCpf("999.999.999-99");
            updated.setActive(false);

            when(employeeRepository.findById(employeeId)).thenReturn(existing);

            useCase.execute(updated);

            verify(employeeRepository, times(1)).save(existing);
        }

        @Test
        void shouldReturnExistingEmployeeInstanceAfterUpdate() {
            UUID employeeId = UUID.randomUUID();

            Employee existing = new Employee();
            existing.setId(employeeId);

            Employee updated = new Employee();
            updated.setId(employeeId);
            updated.setName("New Name");
            updated.setEmail("new@example.com");
            updated.setContact("999999999");
            updated.setCpf("999.999.999-99");
            updated.setActive(true);

            when(employeeRepository.findById(employeeId)).thenReturn(existing);

            Employee result = useCase.execute(updated);

            assertSame(existing, result);
        }

        @Test
        void shouldValidateEmployeeBeforeCheckingExistence() {
            assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));

            verify(employeeRepository, never()).findById(any());
        }

        @Test
        void shouldCheckExistenceBeforeSaving() {
            UUID employeeId = UUID.randomUUID();
            Employee updated = new Employee();
            updated.setId(employeeId);

            when(employeeRepository.findById(employeeId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(updated));

            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, never()).save(any());
        }
    }
}