package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeEntityTest {
    @Test
    void testEmployeeCreation() {
        UUID id = UUID.randomUUID();
        Employee e = new Employee(id, "John Doe", "john@example.com", "12345678", "123.456.789-00", "secret123", true);

        assertEquals(id, e.getId());
        assertEquals("John Doe", e.getName());
        assertEquals("john@example.com", e.getEmail());
        assertEquals("12345678", e.getContact());
        assertEquals("123.456.789-00", e.getCpf());
        assertEquals("secret123", e.getPassword());
        assertTrue(e.getActive());
    }

    @Test
    void testEmployeeInvalidData() {
        // invalid name should throw
        assertThrows(IllegalArgumentException.class, () -> new Employee(UUID.randomUUID(), "", "john@example.com", "12345678", "123.456.789-00", "secret123", true));

        // invalid cpf format
        assertThrows(IllegalArgumentException.class, () -> new Employee(UUID.randomUUID(), "John Doe", "john@example.com", "12345678", "invalid-cpf", "secret123", true));

        // null active throws
        assertThrows(IllegalArgumentException.class, () -> new Employee(UUID.randomUUID(), "John Doe", "john@example.com", "12345678", "123.456.789-00", (Boolean) null));
    }
}
