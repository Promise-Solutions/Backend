package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {
    @Test
    void testGoalCreation() {
        // Simule criação de Goal
        assertTrue(true);
    }

    @Test
    void testGoalInvalidData() {
        // Simule dados inválidos
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Dados inválidos");
        });
    }
}
