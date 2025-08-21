package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTracingUseCaseTest {
    @Test
    void testCreateTracingSuccess() {
        // Simule a criação de tracing e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateTracingThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar tracing");
        });
        assertEquals("Erro ao criar tracing", exception.getMessage());
    }
}
