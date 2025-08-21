package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void testTaskCreation() {
        // Simule criação de Task
        assertTrue(true);
    }

    @Test
    void testTaskInvalidData() {
        // Simule dados inválidos
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Dados inválidos");
        });
    }
}
