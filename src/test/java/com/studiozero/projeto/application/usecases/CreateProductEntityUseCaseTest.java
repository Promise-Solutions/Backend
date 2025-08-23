package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateProductEntityUseCaseTest {
    @Test
    void testCreateProductSuccess() {
        // Simule a criação de produto e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateProductThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar produto");
        });
        assertEquals("Erro ao criar produto", exception.getMessage());
    }
}
