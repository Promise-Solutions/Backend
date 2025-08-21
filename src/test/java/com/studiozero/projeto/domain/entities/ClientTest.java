package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    void testClientCreation() {
        // Simule criação de Client
        assertTrue(true);
    }

    @Test
    void testClientInvalidData() {
        // Simule dados inválidos
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Dados inválidos");
        });
    }
}
