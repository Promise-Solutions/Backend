package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.entities.EmployeeEntity;
import com.studiozero.projeto.infrastructure.mappers.EmployeeEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.EmployeeRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaEmployeeEntityRepositoryTest {

    private JpaEmployeeRepository jpaEmployeeRepository;
    private EmployeeRepositoryImpl employeeRepository;

    @BeforeEach
    void setUp() {
        jpaEmployeeRepository = mock(JpaEmployeeRepository.class);
        employeeRepository = new EmployeeRepositoryImpl(jpaEmployeeRepository);
    }

    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();
        EmployeeEntity entity = mock(EmployeeEntity.class);
        Employee employee = mock(Employee.class);

        when(jpaEmployeeRepository.findById(id)).thenReturn(Optional.of(entity));
        mockStatic(EmployeeEntityMapper.class).when(() -> EmployeeEntityMapper.toDomain(entity)).thenReturn(employee);

        Employee result = employeeRepository.findById(id);
        assertEquals(employee, result);
    }

    @Test
    void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(jpaEmployeeRepository.findById(id)).thenReturn(Optional.empty());

        Employee result = employeeRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testExistsByCpf() {
        String cpf = "123";
        when(jpaEmployeeRepository.existsByCpf(cpf)).thenReturn(true);

        assertTrue(employeeRepository.existsByCpf(cpf));
    }

    @Test
    void testExistsByEmail() {
        String email = "test@email.com";
        when(jpaEmployeeRepository.existsByEmail(email)).thenReturn(true);

        assertTrue(employeeRepository.existsByEmail(email));
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        employeeRepository.deleteById(id);
        verify(jpaEmployeeRepository).deleteById(id);
    }
}