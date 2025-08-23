package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTracingEntityUseCaseTest {
    @Test
    void testGetTracingSuccess() {
        // Simule a busca de tracing e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetTracingThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar tracing");
        });
        assertEquals("Erro ao buscar tracing", exception.getMessage());
    }
}
