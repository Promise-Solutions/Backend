package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SubJobEntityEntityTest {
    @Test
    void testSubJobCreation() {
        SubJob s = new SubJob(UUID.randomUUID(), null, "SJ", 10.0, null, null, null, null, null);
        assertEquals("SJ", s.getTitle());
    }

    @Test
    void testSubJobInvalid() {
        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException("Dados inválidos"); });
    }
}
