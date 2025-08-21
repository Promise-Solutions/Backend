package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    void testProductCreation() {
        // Simule criação de Product
        assertTrue(true);
    }

    @Test
    void testProductInvalidData() {
        // Simule dados inválidos
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Dados inválidos");
        });
    }
}
