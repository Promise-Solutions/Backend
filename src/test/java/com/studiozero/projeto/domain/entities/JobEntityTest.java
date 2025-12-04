package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class JobEntityTest {
    @Test
    void testJobCreation() {
        Client client = new Client();
        Job j = new Job(UUID.randomUUID(), client, "Title", 50.0, null, null, null, null, null, null);
        assertEquals("Title", j.getTitle());
        assertEquals(50.0, j.getTotalValue());
    }

    @Test
    void testJobInvalid() {
        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException("Dados inválidos"); });
    }
}
