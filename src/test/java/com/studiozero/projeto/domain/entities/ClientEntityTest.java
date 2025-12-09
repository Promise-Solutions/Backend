package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.ClientType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClientEntityTest {
    @Test
    void testClientCreation() {
        UUID id = UUID.randomUUID();
        Client c = new Client(id, "Alice", "123.456.789-00", "alice@example.com", "99999999", ClientType.SINGLE, true, LocalDate.of(1990,1,1), LocalDate.now());

        assertEquals(id, c.getId());
        assertEquals("Alice", c.getName());
        assertEquals("123.456.789-00", c.getCpf());
        assertEquals("alice@example.com", c.getEmail());
        assertTrue(c.getActive());
    }

    @Test
    void testClientInvalidCpf() {
        assertThrows(IllegalArgumentException.class, () -> new Client(UUID.randomUUID(), "Bob", "invalid", "b@b.com", "12345678", ClientType.MONTHLY, true, LocalDate.now(), LocalDate.now()));
    }
}
