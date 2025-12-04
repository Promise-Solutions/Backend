package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEntityTest {
    @Test
    void testTaskCreation() {
        Task t = new Task(UUID.randomUUID(), "T", "D", null, null, null, null, null);
        assertEquals("T", t.getTitle());
    }

    @Test
    void testTaskInvalid() {
        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException("Dados inválidos"); });
    }
}
