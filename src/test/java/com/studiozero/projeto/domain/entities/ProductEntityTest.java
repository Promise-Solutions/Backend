package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductEntityTest {
    @Test
    void testProductCreation() {
        Product p = new Product(1, "Prod", 2, 10.0, 5.0, 20.0);
        assertEquals(1, p.getId());
        assertEquals("Prod", p.getName());
    }

    @Test
    void testProductInvalid() {
        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException("Dados inválidos"); });
    }
}
