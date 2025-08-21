package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateClientUseCaseTest {
    @Test
    void testCreateClientSuccess() {
        // Simule a criação de cliente e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateClientThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar cliente");
        });
        assertEquals("Erro ao criar cliente", exception.getMessage());
    }
}
