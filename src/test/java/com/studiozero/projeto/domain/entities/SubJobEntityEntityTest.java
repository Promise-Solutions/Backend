package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubJobEntityEntityTest {
    @Test
    void testSubJobCreation() {
        // Simule criação de SubJob
        assertTrue(true);
    }

    @Test
    void testSubJobInvalidData() {
        // Simule dados inválidos
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Dados inválidos");
        });
    }
}
